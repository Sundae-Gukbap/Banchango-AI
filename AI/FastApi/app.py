from fastapi import FastAPI, HTTPException
from contextlib import asynccontextmanager
from pydantic import BaseModel
import pandas as pd
import numpy as np
from gensim.models import Word2Vec
from sklearn.metrics.pairwise import cosine_similarity

# 사용자로부터 입력받을 데이터 모델 정의
class RecommendRequest(BaseModel):
    ingredients: list[int]
    
class CategoryRecommendRequest(BaseModel):
    ingredients: list[int]
    topcategory: int
    subcategory: str

def convert_ingredients(ingredient_list_str, ingredient_map):
    ingredient_list = eval(ingredient_list_str)
    return [ingredient_map.get(ingr_id, f'Unknown({ingr_id})') for ingr_id in ingredient_list]

def start_load_data():
    global ingredients_df, recipes_df, ingredient_map, model
    ingredients_df = pd.read_csv("last_ingredients.csv")
    recipes_df = pd.read_csv("summary_recipe.csv")
    ingredient_map = ingredients_df.set_index('Index')['Ingredient'].to_dict()
    model = Word2Vec.load('word2vec_recipes.model')
    
    # 레시피 재료 변환
    recipes_df['ConvertedIngredients'] = recipes_df['RecipeIngredient'].apply(lambda x: convert_ingredients(x, ingredient_map))

@asynccontextmanager
async def lifespan(app: FastAPI):
    # When service starts.
    start_load_data()    
    yield

app = FastAPI(lifespan=lifespan)

# 데이터 프레임 및 재료 맵을 전역 변수로 설정 (애플리케이션 시작 시 로드)
ingredients_df = None
recipes_df = None
ingredient_map = None
model = None

def get_recipe_vector(recipe, model):
    word_vectors = [model.wv[word] for word in recipe if word in model.wv]
    return np.mean(word_vectors, axis=0) if word_vectors else np.zeros(model.vector_size)

def get_user_vector(user_ingredients, model):
    return get_recipe_vector(user_ingredients, model)

# 레시피 간 유사도 계산 함수
def word2vec_content_based_recommendations(user_vector, recipe_vectors, top_n=50):
    sim_scores = cosine_similarity([user_vector], list(recipe_vectors.values()))[0]
    sim_scores = list(enumerate(sim_scores))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    similar_recipe_ids = [i[0] for i in sim_scores[:top_n]]  # 가장 유사한 상위 n개의 레시피
    return similar_recipe_ids

@app.post("/category_recommend")
def recommend_recipes(request: CategoryRecommendRequest):
     # 재료 인덱스 목록을 받아 재료명으로 변환
    ingredient_ids = request.ingredients
    ingredients = [ingredient_map.get(ingr_id, f'Unknown({ingr_id})') for ingr_id in ingredient_ids]

    if request.topcategory == 1:
        category_df = recipes_df[recipes_df['by_sort'] == request.subcategory]
    elif request.topcategory == 2:
        category_df = recipes_df[recipes_df['by_situation'] == request.subcategory]
    else:
        raise HTTPException(status_code=400, detail="Invalid topcategory value. Must be 1 or 2.")

    recipe_list = category_df['ConvertedIngredients'].tolist()
    recipe_vectors = {}
    # category_df의 인덱스를 사용하여 각 레시피의 벡터 계산
    for idx, recipe in category_df.iterrows():
        recipe_vectors[idx] = get_recipe_vector(recipe['ConvertedIngredients'], model)
    
    user_vector = get_user_vector(ingredients, model)
    similar_recipes = word2vec_content_based_recommendations(user_vector, recipe_vectors)
    similar_recipe_info = category_df.iloc[similar_recipes]['id'].tolist()

    return {'recommended_recipes': similar_recipe_info}

@app.post("/recommend")
def recommend_recipes(request: RecommendRequest):
     # 재료 인덱스 목록을 받아 재료명으로 변환
    ingredient_ids = request.ingredients
    ingredients = [ingredient_map.get(ingr_id, f'Unknown({ingr_id})') for ingr_id in ingredient_ids]

    recipe_list = recipes_df['ConvertedIngredients'].tolist()
    recipe_vectors = {i: get_recipe_vector(ingredients, model) for i, ingredients in enumerate(recipe_list)}

    user_vector = get_user_vector(ingredients, model)
    similar_recipes = word2vec_content_based_recommendations(user_vector, recipe_vectors)
    similar_recipe_info = recipes_df.loc[similar_recipes, 'id'].tolist()

    return {'recommended_recipes': similar_recipe_info}

# FastAPI 애플리케이션 실행을 위한 main 구문
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
