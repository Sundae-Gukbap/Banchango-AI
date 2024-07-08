package com.sundaegukbap.banchango.ingredient.domain;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="recipe_requiring_ingredient")
public class RecipeRequiringIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Recipe recipe;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ingredient ingredient;
}
