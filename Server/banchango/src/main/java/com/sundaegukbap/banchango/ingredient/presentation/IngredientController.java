package com.sundaegukbap.banchango.ingredient.presentation;

import com.sundaegukbap.banchango.ingredient.application.IngredientQueryService;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
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
@RequestMapping("/api/ingredients")
@Tag(name = "재료 관련 컨트롤러")
public class IngredientController {
    private final IngredientQueryService ingredientQueryService;

    public IngredientController(IngredientQueryService ingredientQueryService) {
        this.ingredientQueryService = ingredientQueryService;
    }

    @GetMapping("/list/{userId}")
    @Operation(description = "소유한 재료 목록을 조회한다.")
    public ResponseEntity<IngredientDetailResponses> getIngredientDetailResponses(@PathVariable("userId") Long userId) {
        IngredientDetailResponses response = ingredientQueryService.getIngredientDetailResponses(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
