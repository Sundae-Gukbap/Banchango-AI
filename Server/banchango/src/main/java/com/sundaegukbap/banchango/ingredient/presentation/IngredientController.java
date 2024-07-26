package com.sundaegukbap.banchango.ingredient.presentation;

import com.sundaegukbap.banchango.ingredient.application.IngredientQueryService;
import com.sundaegukbap.banchango.ingredient.application.IngredientService;
import com.sundaegukbap.banchango.ingredient.dto.*;
import com.sundaegukbap.banchango.ingredient.dto.dto.ContainerIngredientDtos;
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

    @DeleteMapping ("/{containerId}/{ingredientId}")
    @Operation(description = "소유한 재료를 제거한다.")
    public ResponseEntity<String> removeIngredient(@PathVariable("containerId") Long containerId,
                                                   @PathVariable("ingredientId") Long ingredientId) {
        ingredientService.removeIngredient(containerId, ingredientId);
        return new ResponseEntity<>("success remove ingredient", HttpStatus.OK);
    }

    @GetMapping ("/{containerId}/{ingredientId}")
    @Operation(description = "소유한 재료의 상세 정보를 확인한다.") public ResponseEntity<IngredientDetailResponse> getIngredientDetailResponse(@PathVariable("containerId") Long containerId,
                                                   @PathVariable("ingredientId") Long ingredientId) {
        IngredientDetailResponse response = ingredientQueryService.getIngredientDetailResponse(containerId, ingredientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/main/list/{userId}")
    @Operation(description = "사용자가 소유한 전체 재료 목록을 조회한다.")
    public ResponseEntity<ContainerIngredientDtos> getUserIngredients(@PathVariable("userId") Long userId) {
        ContainerIngredientDtos response = ingredientQueryService.getUserIngredients(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/container/list/{containerId}")
    @Operation(description = "특정 창고에 속한 재료 목록을 조회한다.")
    public ResponseEntity<ContainerIngredientDtos> getContainerIngredients(@PathVariable("containerId") Long containerId) {
        ContainerIngredientDtos response = ingredientQueryService.getContainerIngredients(containerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
