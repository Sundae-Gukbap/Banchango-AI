package com.sundaegukbap.banchango.ingredient.application;
import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponse;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponses;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponse;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IngredientQueryService {
    private final ContainerIngredientRepository containerIngredientRepository;

    public IngredientQueryService(ContainerIngredientRepository containerIngredientRepository) {
        this.containerIngredientRepository = containerIngredientRepository;
    }

    public IngredientDetailResponses getIngredientDetailResponses(Long containerId) {
        List<ConatinerIngredient> havingIngredientList = containerIngredientRepository.findAllByContainerId(containerId);

        List<IngredientDetailResponse> ingredientDetailResponseList = havingIngredientList.stream()
                .map(IngredientDetailResponse::of)
                .collect(Collectors.toList());

        return IngredientDetailResponses.of(ingredientDetailResponseList);
    }

    public IngredientDetailResponse getIngredientDetailResponse(Long containerId, Long ingredientId) {
        ConatinerIngredient conatinerIngredient = containerIngredientRepository.findByContainerIdAndIngredientId(containerId, ingredientId)
                .orElseThrow(() -> new NoSuchElementException("user doesn't have ingredient"));
        return IngredientDetailResponse.of(conatinerIngredient);
    }

    public CategoryIngredientResponses getCategoryIngredientResponses(Long containerId) {
        List<ConatinerIngredient> conatinerIngredients = containerIngredientRepository.findAllByContainerId(containerId);

        Map<String, List<ConatinerIngredient>> kindIngredientsMap = conatinerIngredients.stream()
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
