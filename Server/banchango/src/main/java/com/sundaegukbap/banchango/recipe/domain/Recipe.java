package com.sundaegukbap.banchango.recipe.domain;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    String name;
    @NotNull
    @Column(length=2048)
    String introduction;
    @NotNull
    String image;
    @NotNull
    String link;
    @NotNull
    int servings;
    @NotNull
    int cookingTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    Difficulty difficulty;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecipeBookmark> userBookmarkedRecipes;
    @ManyToMany(mappedBy = "recipesWithIngredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ingredient> requiredIngredients = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_recommanded_recipes",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> recommandUsers = new HashSet<>();
}
