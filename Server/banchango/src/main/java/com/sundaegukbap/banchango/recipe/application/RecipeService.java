package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.bookmark.repository.RecipeBookmarkRepository;
import com.sundaegukbap.banchango.ingredient.application.IngredientMatcher;
import com.sundaegukbap.banchango.ingredient.repository.RecipeRequiringIngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
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
    private final IngredientMatcher ingredientMatcher;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, RecipeBookmarkRepository recipeBookmarkRepository, IngredientMatcher ingredientMatcher) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.recipeBookmarkRepository = recipeBookmarkRepository;
        this.ingredientMatcher = ingredientMatcher;
    }

    public List<RecipeDetailResponse> getRecommandedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<RecipeBookmark> recipeBookmarks = recipeBookmarkRepository.findAllByUser(user);
        List<Recipe> recipes = recipeBookmarks.stream()
                .map(b -> b.getRecipe())
                .collect(Collectors.toList());

        //recipe를 순회하면서 user와 have, need간의 관계 파악
        List<RecipeDetailResponse> response = new ArrayList<>();
        for (Recipe recipe : recipes) {
            response.add(getRecipe(user.getId(), recipe.getId()));
        }

        return response;
    }

    public RecipeDetailResponse getRecipe(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        HashMap<String, List> ingredientRelation = ingredientMatcher.checkIngredientRelation(user, recipe);
        List<String> have = ingredientRelation.get("have");
        List<String> need = ingredientRelation.get("need");

        return RecipeDetailResponse.of(recipe, have, need);
    }
}
