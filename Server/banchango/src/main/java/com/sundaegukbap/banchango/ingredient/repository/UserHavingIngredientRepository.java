package com.sundaegukbap.banchango.ingredient.repository;

import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHavingIngredientRepository extends JpaRepository<UserHavingIngredient, Long> {
    List<UserHavingIngredient> findAllByUser(User user);
}
