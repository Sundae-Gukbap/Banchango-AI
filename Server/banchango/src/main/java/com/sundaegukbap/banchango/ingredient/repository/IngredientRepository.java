package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
