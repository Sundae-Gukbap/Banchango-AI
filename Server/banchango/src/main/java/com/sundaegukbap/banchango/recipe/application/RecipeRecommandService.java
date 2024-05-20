package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
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

@Service
public class RecipeRecommandService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeRecommandService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<RecipeDetailResponse> getRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        //TODO AI에게서 레시피들을 받아 온다. List<Long> recipeIds

        List<Long> recipeIds = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findAllById(recipeIds);

        //recipe를 순회하면서 user와 have, need간의 관계 파악
        List<RecipeDetailResponse> response = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<String> have = user.getHavingIngredients().stream()
                    .filter(userIngredient -> recipe.getRequiredIngredients().stream()
                            .anyMatch(recipeIngredient -> recipeIngredient.getName().equals(userIngredient.getName())))
                    .map(Ingredient::getName)
                    .collect(Collectors.toList());

            List<String> need = recipe.getRequiredIngredients().stream()
                    .filter(recipeIngredient -> !user.getHavingIngredients().stream()
                            .anyMatch(userIngredient -> userIngredient.getName().equals(recipeIngredient.getName())))
                    .map(Ingredient::getName)
                    .collect(Collectors.toList());

            boolean isBookmarked = user.getBookmarkedRecipes().stream()
                    .anyMatch(r -> r.getId().equals(recipe.getId()));

            response.add(RecipeDetailResponse.of(recipe, have, need, isBookmarked));
        }

        return response;
    }
}
