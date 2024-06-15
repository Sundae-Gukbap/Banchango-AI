package com.sundaegukbap.banchango.bookmark.application;

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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeBookmarkService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public List<Long> getBookmarkedRecipes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<Long> recipeIds = user.getBookmarkedRecipes().stream()
                .map(b -> b.getId())
                .collect(Collectors.toList());

        return recipeIds;
    }

    public String clickBookmark(Long userId, Long recipeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("no recipe"));

        Set<Recipe> bookmarkedRecipes = user.getBookmarkedRecipes();
        boolean isBookmarked = bookmarkedRecipes.contains(recipe);
        if(!isBookmarked){
            bookmarkedRecipes.add(recipe);
            return "add bookmark";
        } else{
            bookmarkedRecipes.remove(recipe);
            return "delete bookmark";
        }
    }
}
