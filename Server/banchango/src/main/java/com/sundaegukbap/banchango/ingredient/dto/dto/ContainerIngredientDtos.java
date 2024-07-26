package com.sundaegukbap.banchango.ingredient.dto.dto;

import java.util.List;

public record ContainerIngredientDtos(
        List<ContainerIngredientDto> containerIngredientDtos
) {
    public static ContainerIngredientDtos of(List<ContainerIngredientDto> containerIngredientDtoList){
        return new ContainerIngredientDtos(containerIngredientDtoList);
    }
}
