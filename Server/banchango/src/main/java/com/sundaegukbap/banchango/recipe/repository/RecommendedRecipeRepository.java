package com.sundaegukbap.banchango.recipe.repository;

import com.sundaegukbap.banchango.recipe.domain.UserRecommendedRecipe;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedRecipeRepository extends JpaRepository<UserRecommendedRecipe, Long> {
    Page<UserRecommendedRecipe> findAllByUser(Pageable pageable, User user);
    void deleteAllByUserId(Long userId);
}
