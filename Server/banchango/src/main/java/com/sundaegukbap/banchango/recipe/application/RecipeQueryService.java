package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.ingredient.application.IngredientMatcher;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.domain.UserRecommendedRecipe;
import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponse;
import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponses;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.recipe.repository.RecommendedRecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeQueryService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecommendedRecipeRepository recommendedRecipeRepository;
    private final IngredientMatcher ingredientMatcher;

    @Transactional
    public RecommendedRecipeResponse getRecipeDetail(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        return resolveRecipeWithUser(user, recipe);
    }

    @Transactional
    public RecommendedRecipeResponses getRecommendedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<UserRecommendedRecipe> userRecommendedRecipeList = recommendedRecipeRepository.findAllByUser(user);
        List<Recipe> recipes = userRecommendedRecipeList.stream()
                .map(r -> r.getRecipe())
                .collect(Collectors.toList());

        //레시피를 순회하면서 사용자와의 관계 파악해서 RecommendedRecipeResponse추가
        List<RecommendedRecipeResponse> recommendedRecipeResponseList = recipes.stream()
                .map(recipe -> resolveRecipeWithUser(user, recipe))
                .collect(Collectors.toList());

        return RecommendedRecipeResponses.of(recommendedRecipeResponseList);
    }

    public RecommendedRecipeResponse resolveRecipeWithUser(User user, Recipe recipe) {
        HashMap<String, List> ingredientRelation = ingredientMatcher.checkIngredientRelation(user, recipe);
        List<Ingredient> have = ingredientRelation.get("have");
        List<Ingredient> need = ingredientRelation.get("need");

        return RecommendedRecipeResponse.of(recipe, have, need);
    }
}
