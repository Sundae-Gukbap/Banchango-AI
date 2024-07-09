package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRequiringIngredientRepository extends JpaRepository<RecipeRequiringIngredient, Long> {
    List<RecipeRequiringIngredient> findAllByRecipe(Recipe recipe);
}
