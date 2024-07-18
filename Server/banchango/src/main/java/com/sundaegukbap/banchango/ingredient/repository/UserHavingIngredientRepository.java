package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHavingIngredientRepository extends JpaRepository<UserHavingIngredient, Long> {
    Optional<UserHavingIngredient> findByUserAndIngredient(User user, Ingredient ingredient);
    Optional<UserHavingIngredient> findByUserIdAndIngredientId(Long userId, Long ingredientId);
    List<UserHavingIngredient> findAllByUser(User user);
    List<UserHavingIngredient> findAllByUserId(Long userId);

}
