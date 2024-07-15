package com.sundaegukbap.banchango.bookmark.presentation;

import com.sundaegukbap.banchango.bookmark.application.RecipeBookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark/recipe")
@Tag(name = "레시피 북마크 관련 컨트롤러")
public class RecipeBookmarkController {
    private final RecipeBookmarkService recipeBookmarkService;

    public RecipeBookmarkController(RecipeBookmarkService recipeBookmarkService) {
        this.recipeBookmarkService = recipeBookmarkService;
    }

    @GetMapping("/{userId}")
    @Operation(description = "레시피 북마크 리스트를 조회한다.")
    public ResponseEntity<List> getRecommandRecipes(@PathVariable("userId") Long userId) {
        List<Long> response = recipeBookmarkService.getBookmarkedRecipes(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/{recipeId}")
    @Operation(description = "레시피 북마크를 할당/해제 한다.")
    public ResponseEntity<String> getRecipeDetail(@PathVariable("userId") Long userId, @PathVariable("recipeId") Long recipeId) {
        String response = recipeBookmarkService.clickBookmark(userId,recipeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}