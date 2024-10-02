package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.RecipeRequiringIngredientRepository;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class IngredientMatcher {
    private ContainerRepository containerRepository;
    private ContainerIngredientRepository containerIngredientRepository;
    private RecipeRequiringIngredientRepository recipeRequiringIngredientRepository;

    public HashMap<String,List> checkIngredientRelation(User user, Recipe recipe){
        List<Ingredient> havingIngredients = getAllIngredientsWithUser(user);
        List<Ingredient> requiringIngredients = getIngredientsWithRecipe(recipe);

        List<Ingredient> need = new ArrayList<>();
        List<Ingredient> have = new ArrayList<>();

        for(Ingredient ri : requiringIngredients){
            boolean has = havingIngredients.contains(ri);
            if(has)
                have.add(ri);
            else
                need.add(ri);
        }

        HashMap<String,List> result = new HashMap<>();
        result.put("need", need);
        result.put("have", have);

        return result;
    }

    private List<Ingredient> getAllIngredientsWithUser(User user){
        List<Container> containers = containerRepository.findAllByUser(user);
        List<ContainerIngredient> containerIngredientList = containerIngredientRepository.findByContainerIn(containers);
        List<Ingredient> ingredients = containerIngredientList.stream()
                .map(ContainerIngredient::getIngredient)
                .collect(Collectors.toList());

        return ingredients;
    }

    private List<Ingredient> getIngredientsWithRecipe(Recipe recipe) {
        List<RecipeRequiringIngredient> recipeRequiringIngredientList = recipeRequiringIngredientRepository.findAllByRecipe(recipe);

        return recipeRequiringIngredientList.stream()
                .map(i -> i.getIngredient())
                .collect(Collectors.toList());
    }
}
