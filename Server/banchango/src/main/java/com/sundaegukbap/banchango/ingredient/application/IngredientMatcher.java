package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;
import com.sundaegukbap.banchango.ingredient.repository.RecipeRequiringIngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMatcher {
    private UserHavingIngredientRepository userHavingIngredientRepository;
    private RecipeRequiringIngredientRepository recipeRequiringIngredientRepository;

    public IngredientMatcher(UserHavingIngredientRepository userHavingIngredientRepository, RecipeRequiringIngredientRepository recipeRequiringIngredientRepository) {
        this.userHavingIngredientRepository = userHavingIngredientRepository;
        this.recipeRequiringIngredientRepository = recipeRequiringIngredientRepository;
    }

    public HashMap<String,List> checkIngredientRelation(User user, Recipe recipe){
        List<ConatinerIngredient> havingIngredients = userHavingIngredientRepository.findAllByUser(user);
        List<RecipeRequiringIngredient> requiringIngredients = recipeRequiringIngredientRepository.findAllByRecipe(recipe);

        List<String> need = getNeedIngredients(havingIngredients, requiringIngredients);
        List<String> have = getHaveIngredients(havingIngredients, requiringIngredients);

        HashMap<String,List> result = new HashMap<>();
        result.put("need", need);
        result.put("have", have);

        return result;
    }

    private List<String> getNeedIngredients(List<ConatinerIngredient> havingIngredients, List<RecipeRequiringIngredient> requiringIngredients) {
        return requiringIngredients.stream()
                .filter(ri -> havingIngredients.stream()
                        .anyMatch(hi -> !ri.getIngredient().equals(hi.getIngredient())))
                .map(i -> i.getIngredient().getName())
                .collect(Collectors.toList());
    }

    private List<String> getHaveIngredients(List<ConatinerIngredient> havingIngredients, List<RecipeRequiringIngredient> requiringIngredients) {
        return requiringIngredients.stream()
                .filter(ri -> havingIngredients.stream()
                        .anyMatch(hi -> ri.getIngredient().equals(hi.getIngredient())))
                .map(i -> i.getIngredient().getName())
                .collect(Collectors.toList());
    }
}
