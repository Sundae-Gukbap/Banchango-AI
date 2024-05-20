package com.sundaegukbap.banchango.recipe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 50)
    String name;
    @NotNull
    @Size(max = 255)
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
}
