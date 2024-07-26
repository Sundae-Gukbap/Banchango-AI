package com.sundaegukbap.banchango.ingredient.domain;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="recipe_requiring_ingredients")
public class RecipeRequiringIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Builder
    public RecipeRequiringIngredient(Long id, Recipe recipe, Ingredient ingredient) {
        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}
