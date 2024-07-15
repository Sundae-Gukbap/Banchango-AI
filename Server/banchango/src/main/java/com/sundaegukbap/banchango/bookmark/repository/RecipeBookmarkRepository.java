package com.sundaegukbap.banchango.bookmark.repository;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeBookmarkRepository extends JpaRepository<RecipeBookmark, Long> {
    List<RecipeBookmark> findAllByUser(User user);

    Optional<RecipeBookmark> findByUserAndRecipe(User user, Recipe recipe);
}
