package com.sundaegukbap.banchango.presentation;

import com.sundaegukbap.banchango.recipe.application.RecipeRecommandService;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
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
@RequestMapping("/recipe/recommand")
@Tag(name = "레시피 추천 관련 컨트롤러")
public class RecipeRecommandController {
    private final RecipeRecommandService recipeRecommandService;

    public RecipeRecommandController(RecipeRecommandService recipeRecommandService) {
        this.recipeRecommandService = recipeRecommandService;
    }

    @GetMapping("/{userId}")
    @Operation(description = "추천 레시피를 조회한다.")
    public ResponseEntity<List> findAll(@PathVariable("userId") Long userId) {
        List<RecipeDetailResponse> response = recipeRecommandService.getRecipes(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
