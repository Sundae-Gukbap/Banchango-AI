package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.IngredientInsertRequest;
import com.sundaegukbap.banchango.ingredient.repository.IngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class IngredientService {
    private final UserHavingIngredientRepository userHavingIngredientRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientService(UserHavingIngredientRepository userHavingIngredientRepository, UserRepository userRepository, IngredientRepository ingredientRepository) {
        this.userHavingIngredientRepository = userHavingIngredientRepository;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void insertIngredient(Long userId, IngredientInsertRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        Ingredient ingredient = ingredientRepository.findById(request.ingredientId())
                .orElseThrow(() -> new NoSuchElementException("no ingredient"));

        ConatinerIngredient conatinerIngredient = request.toEntity(user, ingredient);
        userHavingIngredientRepository.save(conatinerIngredient);
    }

    public void removeIngredient(Long userId, Long ingredientId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new NoSuchElementException("no ingredient"));

        ConatinerIngredient conatinerIngredient = userHavingIngredientRepository.findByUserAndIngredient(user, ingredient)
                .orElseThrow(() -> new NoSuchElementException("user doesn't have ingredient"));
        userHavingIngredientRepository.delete(conatinerIngredient);
    }
}
