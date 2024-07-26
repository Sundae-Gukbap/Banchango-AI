package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.repository.RecipeBookmarkRepository;
import com.sundaegukbap.banchango.ingredient.application.IngredientMatcher;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.domain.UserRecommandedRecipe;
import com.sundaegukbap.banchango.recipe.dto.RecommandedRecipeResponse;
import com.sundaegukbap.banchango.recipe.dto.RecommandedRecipeResponses;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.recipe.repository.RecommandedRecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//recipe와 관련된 조작을 수행한다.
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecipeBookmarkRepository recipeBookmarkRepository;
    private final RecommandedRecipeRepository recommandedRecipeRepository;
    private final IngredientMatcher ingredientMatcher;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, RecipeBookmarkRepository recipeBookmarkRepository, RecommandedRecipeRepository recommandedRecipeRepository, IngredientMatcher ingredientMatcher) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.recipeBookmarkRepository = recipeBookmarkRepository;
        this.recommandedRecipeRepository = recommandedRecipeRepository;
        this.ingredientMatcher = ingredientMatcher;
    }

    public RecommandedRecipeResponse getRecipeDetail(Long userId, Long recipeId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        return resolveRecipeWithUser(user, recipe);
    }

    public RecommandedRecipeResponses getRecommandedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<UserRecommandedRecipe> userRecommandedRecipeList = recommandedRecipeRepository.findAllByUser(user);
        List<Recipe> recipes = userRecommandedRecipeList.stream()
                .map(r -> r.getRecipe())
                .collect(Collectors.toList());

        //레시피를 순회하면서 사용자와의 관계 파악해서 RecommandedRecipeResponse추가
        List<RecommandedRecipeResponse> recommandedRecipeResponseList = recipes.stream()
                .map(recipe -> resolveRecipeWithUser(user, recipe))
                .collect(Collectors.toList());

        return RecommandedRecipeResponses.of(recommandedRecipeResponseList);
    }

    public RecommandedRecipeResponse resolveRecipeWithUser(User user, Recipe recipe) {
        HashMap<String, List> ingredientRelation = ingredientMatcher.checkIngredientRelation(user, recipe);
        List<Ingredient> have = ingredientRelation.get("have");
        List<Ingredient> need = ingredientRelation.get("need");

        return RecommandedRecipeResponse.of(recipe, have, need);
    }
}
