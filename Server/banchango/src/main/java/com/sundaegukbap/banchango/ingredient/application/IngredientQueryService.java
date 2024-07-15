package com.sundaegukbap.banchango.ingredient.application;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponse;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
import com.sundaegukbap.banchango.ingredient.repository.IngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class IngredientQueryService {
    private final IngredientRepository ingredientRepository;
    private final UserHavingIngredientRepository userHavingIngredientRepository;

    public IngredientQueryService(IngredientRepository ingredientRepository, UserHavingIngredientRepository userHavingIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.userHavingIngredientRepository = userHavingIngredientRepository;
    }

    public IngredientDetailResponses getIngredientDetailResponses(Long userId) {
        List<UserHavingIngredient> havingIngredientList = userHavingIngredientRepository.findAllByUserId(userId);

        List<IngredientDetailResponse> ingredientDetailResponseList = havingIngredientList.stream()
                .map(IngredientDetailResponse::of)
                .collect(Collectors.toList());

        return IngredientDetailResponses.of(ingredientDetailResponseList);
    }
}
