package com.sundaegukbap.banchango.ingredient.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max=50)
    String name;
    @Size(max=50)
    String kind;

    @Builder
    public Ingredient(Long id, String name, String kind) {
        this.id = id;
        this.name = name;
        this.kind = kind;
    }
}
