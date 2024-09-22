package com.sundaegukbap.banchango.container.presentation;

import com.sundaegukbap.banchango.container.application.ContainerService;
import com.sundaegukbap.banchango.container.dto.ContainerInsertRequest;
import com.sundaegukbap.banchango.ingredient.dto.IngredientInsertRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/container")
@Tag(name = "재료 관련 컨트롤러")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping("/{userId}")
    @Operation(summary = "식자재 창고 추가", description = "식자재 창고를 추가한다.")
    public ResponseEntity<String> createContainer(@PathVariable("userId") Long userId,
                                                   @RequestBody ContainerInsertRequest request) {
        containerService.createContainer(userId, request);
        return new ResponseEntity<>("success add container", HttpStatus.OK);
    }
}
