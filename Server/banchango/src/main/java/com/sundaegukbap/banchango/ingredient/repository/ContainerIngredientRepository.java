package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainerIngredientRepository extends JpaRepository<ContainerIngredient, Long> {
    Optional<ContainerIngredient> findByContainerAndIngredient(Container container, Ingredient ingredient);
    Optional<ContainerIngredient> findByContainerIdAndIngredientId(Long containerId, Long ingredientId);
    List<ContainerIngredient> findAllByContainer(Container container);
    List<ContainerIngredient> findAllByContainerId(Long containerId);

}
