package com.sundaegukbap.banchango.recipe.presentation;

import com.sundaegukbap.banchango.recipe.application.RecipeService;
import com.sundaegukbap.banchango.recipe.dto.RecommandedRecipeResponse;
import com.sundaegukbap.banchango.recipe.dto.RecommandedRecipeResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@Tag(name = "레시피 관련 컨트롤러")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recommand/{userId}")
    @Operation(description = "추천 레시피를 조회한다.")
    public ResponseEntity<RecommandedRecipeResponses> getRecommandedRecipes(@PathVariable("userId") Long userId) {
        RecommandedRecipeResponses response = recipeService.getRecommandedRecipes(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{recipeId}")
    @Operation(description = "레시피를 조회한다.")
    public ResponseEntity<RecommandedRecipeResponse> getRecipeDetail(@PathVariable("userId") Long userId, @PathVariable("recipeId") Long recipeId) {
        RecommandedRecipeResponse response = recipeService.getRecipeDetail(userId,recipeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
