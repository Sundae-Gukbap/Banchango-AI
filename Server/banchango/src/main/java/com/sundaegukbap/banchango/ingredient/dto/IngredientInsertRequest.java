package com.sundaegukbap.banchango.ingredient.dto;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.user.domain.User;

import java.time.LocalDateTime;

public record IngredientInsertRequest(
        Long ingredientId,
        LocalDateTime expirationDate
) {
    public UserHavingIngredient toEntity(User user, Ingredient ingredient){
        return UserHavingIngredient.builder()
                .id(null)
                .user(user)
                .ingredient(ingredient)
                .createdAt(null)
                .expriationDate(this.expirationDate)
                .build();
    }
}
