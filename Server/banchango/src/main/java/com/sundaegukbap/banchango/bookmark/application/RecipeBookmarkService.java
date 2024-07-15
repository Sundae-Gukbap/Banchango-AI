package com.sundaegukbap.banchango.bookmark.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.bookmark.repository.RecipeBookmarkRepository;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeBookmarkService {
    private final RecipeBookmarkRepository recipeBookmarkRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public List<Long> getBookmarkedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException());

        List<RecipeBookmark> recipeBookmarks = recipeBookmarkRepository.findAllByUser(user);

        List<Long> result = recipeBookmarks.stream()
                .map(b -> b.getRecipe().getId())
                .collect(Collectors.toList());

        return result;
    }

    public String clickBookmark(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        Optional<RecipeBookmark> optionalRecipeBookmark = recipeBookmarkRepository.findByUserAndRecipe(user, recipe);
        if(optionalRecipeBookmark.isEmpty()){
            recipeBookmarkRepository.save(new RecipeBookmark(user, recipe));
            return "add bookmark";
        } else{
            recipeBookmarkRepository.delete(optionalRecipeBookmark.get());
            return "delete bookmark";
        }
    }
}
