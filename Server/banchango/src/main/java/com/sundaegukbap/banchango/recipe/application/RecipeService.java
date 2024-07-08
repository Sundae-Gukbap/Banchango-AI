package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//recipe와 관련된 조작을 수행한다.
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<RecipeDetailResponse> getRecommandedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<Recipe> recipes = user.getBookmarkedRecipes().stream()
                .map(r -> r.getRecipe())
                .collect(Collectors.toList());

        //recipe를 순회하면서 user와 have, need간의 관계 파악
        List<RecipeDetailResponse> response = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<String> have = getHaveIngredients(user,recipe);
            List<String> need = getNeedIngredients(user,recipe);

            response.add(RecipeDetailResponse.of(recipe, have, need));
        }

        return response;
    }

    //TODO bookmark
    public RecipeDetailResponse getRecipe(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        List<String> have = getHaveIngredients(user, recipe);
        List<String> need = getNeedIngredients(user, recipe);

        return RecipeDetailResponse.of(recipe, have, need);
    }

    private static List<String> getNeedIngredients(User user, Recipe recipe) {
        List<UserHavingIngredient> havingIngredients = user.getHavingIngredients();
        List<RecipeRequiringIngredient> requiringIngredients = recipe.getRequiringIngredients();

        List<String> need = havingIngredients.stream()
                .filter(hi -> requiringIngredients.stream()
                        .anyMatch(ri -> !hi.getIngredient().equals(ri.getIngredient())))
                .map(i -> i.getIngredient().getName())
                .collect(Collectors.toList());

        return need;
    }

    private static List<String> getHaveIngredients(User user, Recipe recipe) {
        List<UserHavingIngredient> havingIngredients = user.getHavingIngredients();
        List<RecipeRequiringIngredient> requiringIngredients = recipe.getRequiringIngredients();

        List<String> have = havingIngredients.stream()
                .filter(hi -> requiringIngredients.stream()
                        .anyMatch(ri -> hi.getIngredient().equals(ri.getIngredient())))
                .map(i -> i.getIngredient().getName())
                .collect(Collectors.toList());

        return have;
    }
}
