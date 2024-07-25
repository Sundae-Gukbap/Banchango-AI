package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainerIngredientRepository extends JpaRepository<ConatinerIngredient, Long> {
    Optional<ConatinerIngredient> findByContainerAndIngredient(Container container, Ingredient ingredient);
    Optional<ConatinerIngredient> findByContainerIdAndIngredientId(Long containerId, Long ingredientId);
    List<ConatinerIngredient> findAllByContainer(Container container);
    List<ConatinerIngredient> findAllByContainerId(Long containerId);

}
