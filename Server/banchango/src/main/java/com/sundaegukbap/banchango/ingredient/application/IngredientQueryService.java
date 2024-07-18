package com.sundaegukbap.banchango.ingredient.application;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponse;
import com.sundaegukbap.banchango.ingredient.dto.CategoryIngredientResponses;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponse;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IngredientQueryService {
    private final UserHavingIngredientRepository userHavingIngredientRepository;

    public IngredientQueryService(UserHavingIngredientRepository userHavingIngredientRepository) {
        this.userHavingIngredientRepository = userHavingIngredientRepository;
    }

    public IngredientDetailResponses getIngredientDetailResponses(Long userId) {
        List<UserHavingIngredient> havingIngredientList = userHavingIngredientRepository.findAllByUserId(userId);

        List<IngredientDetailResponse> ingredientDetailResponseList = havingIngredientList.stream()
                .map(IngredientDetailResponse::of)
                .collect(Collectors.toList());

        return IngredientDetailResponses.of(ingredientDetailResponseList);
    }

    public IngredientDetailResponse getIngredientDetailResponse(Long userId, Long ingredientId) {
        UserHavingIngredient userHavingIngredient = userHavingIngredientRepository.findByUserIdAndIngredientId(userId, ingredientId)
                .orElseThrow(() -> new NoSuchElementException("user doesn't have ingredient"));
        return IngredientDetailResponse.of(userHavingIngredient);
    }

    public CategoryIngredientResponses getCategoryIngredientResponses(Long userId) {
        List<UserHavingIngredient> userHavingIngredients = userHavingIngredientRepository.findAllByUserId(userId);

        Map<String, List<UserHavingIngredient>> kindIngredientsMap = userHavingIngredients.stream()
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
