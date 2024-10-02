package com.sundaegukbap.banchango.recipe.presentation;

import com.sundaegukbap.banchango.recipe.application.RecipeService;
import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponse;
import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@AllArgsConstructor
@Tag(name = "레시피 관련 컨트롤러")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/recommend/{userId}")
    @Operation(summary = "추천 레시피 목록 조회", description = "추천 레시피 목록을 조회한다.")
    public ResponseEntity<RecommendedRecipeResponses> getRecommendedRecipes(@PathVariable("userId") Long userId) {
        RecommendedRecipeResponses response = recipeService.getRecommendedRecipes(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{recipeId}")
    @Operation(summary = "특정 레시피 상세 조회", description = "레시피를 조회한다.")
    public ResponseEntity<RecommendedRecipeResponse> getRecipeDetail(@PathVariable("userId") Long userId, @PathVariable("recipeId") Long recipeId) {
        RecommendedRecipeResponse response = recipeService.getRecipeDetail(userId,recipeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
