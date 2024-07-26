package com.sundaegukbap.banchango.ingredient.dto.dto;

import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;

import java.util.List;
import java.util.stream.Collectors;

public record ContainerIngredientDtos(
        List<ContainerIngredientDto> containerIngredientDtos
) {

    public static ContainerIngredientDtos of(List<ContainerIngredient> containerIngredientList){
        List<ContainerIngredientDto> containerIngredientDtoList = containerIngredientList.stream()
                .map(ContainerIngredientDto::of)
                .collect(Collectors.toList());
        return new ContainerIngredientDtos(containerIngredientDtoList);

    }
}
