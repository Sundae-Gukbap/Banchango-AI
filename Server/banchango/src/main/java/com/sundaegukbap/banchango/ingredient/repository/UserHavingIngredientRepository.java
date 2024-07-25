package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.ConatinerIngredient;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHavingIngredientRepository extends JpaRepository<ConatinerIngredient, Long> {
    Optional<ConatinerIngredient> findByUserAndIngredient(User user, Ingredient ingredient);
    Optional<ConatinerIngredient> findByUserIdAndIngredientId(Long userId, Long ingredientId);
    List<ConatinerIngredient> findAllByUser(User user);
    List<ConatinerIngredient> findAllByUserId(Long userId);

}
