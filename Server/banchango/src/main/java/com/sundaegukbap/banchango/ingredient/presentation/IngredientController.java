package com.sundaegukbap.banchango.ingredient.presentation;

import com.sundaegukbap.banchango.ingredient.application.IngredientQueryService;
import com.sundaegukbap.banchango.ingredient.application.IngredientService;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponse;
import com.sundaegukbap.banchango.ingredient.dto.IngredientDetailResponses;
import com.sundaegukbap.banchango.ingredient.dto.IngredientInsertRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
@Tag(name = "재료 관련 컨트롤러")
public class IngredientController {
    private final IngredientQueryService ingredientQueryService;
    private final IngredientService ingredientService;

    public IngredientController(IngredientQueryService ingredientQueryService, IngredientService ingredientService) {
        this.ingredientQueryService = ingredientQueryService;
        this.ingredientService = ingredientService;
    }

    @PostMapping ("/{userId}")
    @Operation(description = "소유한 재료를 추가한다.")
    public ResponseEntity<String> insertIngredient(@PathVariable("userId") Long userId,
                                                   @RequestBody IngredientInsertRequest request) {
        ingredientService.insertIngredient(userId, request);
        return new ResponseEntity<>("success add ingredient", HttpStatus.OK);
    }

    @DeleteMapping ("/{userId}/{ingredientId}")
    @Operation(description = "소유한 재료를 제거한다.")
    public ResponseEntity<String> removeIngredient(@PathVariable("userId") Long userId,
                                                   @PathVariable("ingredientId") Long ingredientId) {
        ingredientService.removeIngredient(userId, ingredientId);
        return new ResponseEntity<>("success remove ingredient", HttpStatus.OK);
    }

    @GetMapping ("/{userId}/{ingredientId}")
    @Operation(description = "소유한 재료의 상세 정보를 확인한다.")
    public ResponseEntity<IngredientDetailResponse> getIngredientDetailResponse(@PathVariable("userId") Long userId,
                                                   @PathVariable("ingredientId") Long ingredientId) {
        IngredientDetailResponse response = ingredientQueryService.getIngredientDetailResponse(userId, ingredientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    @Operation(description = "소유한 재료 목록을 조회한다.")
    public ResponseEntity<IngredientDetailResponses> getIngredientDetailResponses(@PathVariable("userId") Long userId) {
        IngredientDetailResponses response = ingredientQueryService.getIngredientDetailResponses(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
