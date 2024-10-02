package com.sundaegukbap.banchango.recipe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="recipes")
public class Recipe {
    @Id
    private Long id;
    @NotNull
    private String name;
    private String bestName;
    @NotNull
    @Column(length=2048)
    private String introduction;
    @NotNull
    private String image1;
    private String image2;
    @NotNull
    private int servings;
    @NotNull
    private int cookingTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RecipeDifficulty recipeDifficulty;
    private String bySort;
    private String byIngredient;
    private RecipeCategory recipeCategory;

    @Builder
    public Recipe(Long id, String name, String bestName, String introduction, String image1, String image2, String link, int servings, int cookingTime, RecipeDifficulty recipeDifficulty, String bySort, String byIngredient, RecipeCategory recipeCategory) {
        this.id = id;
        this.name = name;
        this.bestName = bestName;
        this.introduction = introduction;
        this.image1 = image1;
        this.image2 = image2;
        this.servings = servings;
        this.cookingTime = cookingTime;
        this.recipeDifficulty = recipeDifficulty;
        this.bySort = bySort;
        this.byIngredient = byIngredient;
        this.recipeCategory = recipeCategory;
    }

    public String getLink(){
        return "https://m.10000recipe.com/recipe/"+this.id;
    }
}
