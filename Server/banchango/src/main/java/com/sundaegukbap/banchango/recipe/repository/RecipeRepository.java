package com.sundaegukbap.banchango.recipe.repository;

import com.sundaegukbap.banchango.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipe ORDER BY RAND() LIMIT :size", nativeQuery = true)
    List<Recipe> findRecipesByRandom(@Param("size") int size);
}
