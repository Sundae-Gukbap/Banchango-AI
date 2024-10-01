package com.sundaegukbap.banchango.ingredient.application;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.dto.dto.ContainerIngredientDto;
import com.sundaegukbap.banchango.ingredient.dto.dto.ContainerIngredientDtos;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public ContainerIngredientDto getIngredientInfo(Long containerIngredientId) {
        ContainerIngredient containerIngredient = containerIngredientRepository.findById(containerIngredientId)
                .orElseThrow(() -> new NoSuchElementException("no ingredient in container"));
        return ContainerIngredientDto.of(containerIngredient);
    }
}
