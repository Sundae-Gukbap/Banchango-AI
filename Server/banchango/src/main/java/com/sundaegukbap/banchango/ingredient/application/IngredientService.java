package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.IngredientInsertRequest;
import com.sundaegukbap.banchango.ingredient.repository.IngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class IngredientService {
    private final ContainerIngredientRepository containerIngredientRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final ContainerRepository containerRepository;

    public IngredientService(ContainerIngredientRepository containerIngredientRepository, UserRepository userRepository, IngredientRepository ingredientRepository, ContainerRepository containerRepository) {
        this.containerIngredientRepository = containerIngredientRepository;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
        this.containerRepository = containerRepository;
    }

    public void insertIngredient(Long userId, IngredientInsertRequest request) {
        Container container = containerRepository.findById(request.containerId())
                .orElseThrow(() -> new NoSuchElementException("no container"));
        Ingredient ingredient = ingredientRepository.findById(request.ingredientId())
                .orElseThrow(() -> new NoSuchElementException("no ingredient"));

        ContainerIngredient containerIngredient = request.toEntity(container, ingredient);
        containerIngredientRepository.save(containerIngredient);
    }

    public void removeIngredient(Long containerId, Long ingredientId) {
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new NoSuchElementException("no container"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new NoSuchElementException("no ingredient"));

        ContainerIngredient containerIngredient = containerIngredientRepository.findByContainerAndIngredient(container, ingredient)
                .orElseThrow(() -> new NoSuchElementException("user doesn't have ingredient"));
        containerIngredientRepository.delete(containerIngredient);
    }
}
