package com.sundaegukbap.banchango.ingredient.application;
import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponse;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponses;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponse;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
import com.sundaegukbap.banchango.ingredient.dto.dto.ContainerIngredientDto;
import com.sundaegukbap.banchango.ingredient.dto.dto.ContainerIngredientDtos;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IngredientQueryService {
    private final ContainerRepository containerRepository;
    private final ContainerIngredientRepository containerIngredientRepository;

    public IngredientQueryService(ContainerRepository containerRepository, ContainerIngredientRepository containerIngredientRepository) {
        this.containerRepository = containerRepository;
        this.containerIngredientRepository = containerIngredientRepository;
    }

    public ContainerIngredientDtos getUserIngredients(Long userId) {
        List<Container> containerList = containerRepository.findAllByUserId(userId);
        List<ContainerIngredient> containerIngredientList = containerIngredientRepository.findByContainerIn(containerList);

        return ContainerIngredientDtos.of(containerIngredientList);
    }

    public ContainerIngredientDtos getContainerIngredients(Long containerId) {
        List<ContainerIngredient> containerIngredientList = containerIngredientRepository.findAllByContainerId(containerId);

        return ContainerIngredientDtos.of(containerIngredientList);
    }

    public IngredientDetailResponse getIngredientDetailResponse(Long containerId, Long ingredientId) {
        ContainerIngredient containerIngredient = containerIngredientRepository.findByContainerIdAndIngredientId(containerId, ingredientId)
                .orElseThrow(() -> new NoSuchElementException("user doesn't have ingredient"));
        return IngredientDetailResponse.of(containerIngredient);
    }

    public CategoryIngredientResponses getCategoryIngredientResponses(Long containerId) {
        List<ContainerIngredient> containerIngredients = containerIngredientRepository.findAllByContainerId(containerId);

        Map<String, List<ContainerIngredient>> kindIngredientsMap = containerIngredients.stream()
                .collect(Collectors.groupingBy(userHavingIngredient -> userHavingIngredient.getIngredient().getKind()));

        List<CategoryIngredientResponse> categoryIngredientResponseList = new ArrayList<>();
        kindIngredientsMap.forEach((kind, userHavingIngredientsList) -> {
            List<IngredientDetailResponse> ingredientDetailResponseList = userHavingIngredientsList.stream()
                    .map(IngredientDetailResponse::of)
                    .collect(Collectors.toList());

            IngredientDetailResponses ingredientDetailResponses = IngredientDetailResponses.of(ingredientDetailResponseList);

            categoryIngredientResponseList.add(CategoryIngredientResponse.of(kind, ingredientDetailResponses));
        });

        return CategoryIngredientResponses.of(categoryIngredientResponseList);
    }


}
