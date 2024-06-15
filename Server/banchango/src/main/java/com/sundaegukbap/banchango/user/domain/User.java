package com.sundaegukbap.banchango.user.domain;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecipeBookmark> userBookmarkedRecipes;

    @ManyToMany(mappedBy = "ingredientHolders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ingredient> havingIngredients = new HashSet<>();
    @ManyToMany(mappedBy = "recommandUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recipe> recommandedRecipes = new HashSet<>();
}
