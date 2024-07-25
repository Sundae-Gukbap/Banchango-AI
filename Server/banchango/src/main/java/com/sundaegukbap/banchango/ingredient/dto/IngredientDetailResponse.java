package com.sundaegukbap.banchango.ingredient.dto;

import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;

import java.time.LocalDateTime;

//유저가 소유한 재료 상세 정보
public record IngredientDetailResponse (
        Long ingredientId,
        String name,
        String kind,
        LocalDateTime createdAt,
        LocalDateTime expirationDate
){
    public static IngredientDetailResponse of(ConatinerIngredient havingIngredient){
        return new IngredientDetailResponse(
                havingIngredient.getIngredient().getId(),
                havingIngredient.getIngredient().getName(),
                havingIngredient.getIngredient().getKind(),
                havingIngredient.getCreatedAt(),
                havingIngredient.getExpriationDate()
        );
    }
}
