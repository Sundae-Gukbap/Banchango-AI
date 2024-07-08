package com.sundaegukbap.banchango.recipe.domain;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeBookmark> userBookmarkedRecipes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeRequiringIngredient> requiringIngredients;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserRecommandedRecipe> recommandUsers;

    @Builder
    public Recipe(Long id, String name, String introduction, String image, String link, int servings, int cookingTime, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.image = image;
        this.link = link;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
    }
}
