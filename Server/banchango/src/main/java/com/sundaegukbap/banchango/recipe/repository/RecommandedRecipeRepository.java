package com.sundaegukbap.banchango.recipe.repository;

import com.sundaegukbap.banchango.recipe.domain.UserRecommandedRecipe;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommandedRecipeRepository extends JpaRepository<UserRecommandedRecipe, Long> {
    List<UserRecommandedRecipe> findAllByUser(User user);
}
