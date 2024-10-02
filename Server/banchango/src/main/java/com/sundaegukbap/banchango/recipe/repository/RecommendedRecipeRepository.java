package com.sundaegukbap.banchango.recipe.repository;

import com.sundaegukbap.banchango.recipe.domain.UserRecommendedRecipe;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedRecipeRepository extends JpaRepository<UserRecommendedRecipe, Long> {
    List<UserRecommendedRecipe> findAllByUser(User user);
    void deleteAllByUserId(Long userId);
}
