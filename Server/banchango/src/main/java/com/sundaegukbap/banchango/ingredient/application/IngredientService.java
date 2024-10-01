package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.IngredientInsertRequest;
import com.sundaegukbap.banchango.ingredient.repository.IngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import com.sundaegukbap.banchango.ai.application.AiRecipeRecommendService;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class IngredientService {
    private final ContainerIngredientRepository containerIngredientRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final ContainerRepository containerRepository;
    private final AiRecipeRecommendService aiRecipeRecommendService;

    public void insertIngredient(Long userId, IngredientInsertRequest request) {
        Container container = containerRepository.findById(request.containerId())
                .orElseThrow(() -> new NoSuchElementException("no container"));
        Ingredient ingredient = ingredientRepository.findById(request.ingredientId())
                .orElseThrow(() -> new NoSuchElementException("no ingredient"));

        ContainerIngredient containerIngredient = request.toEntity(container, ingredient);
        containerIngredientRepository.save(containerIngredient);
    }

    public void removeIngredient(Long containerIngredientId) {
        ContainerIngredient containerIngredient = containerIngredientRepository.findById(containerIngredientId)
                .orElseThrow(() -> new NoSuchElementException("no ingredient in container"));

        containerIngredientRepository.delete(containerIngredient);
    }
}
