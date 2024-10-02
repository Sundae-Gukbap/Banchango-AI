package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.ai.application.AiRecipeRecommendClient;
import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.ingredient.domain.ContainerIngredient;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.dto.event.IngredientChangedEvent;
import com.sundaegukbap.banchango.ingredient.repository.ContainerIngredientRepository;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.domain.RecipeCategory;
import com.sundaegukbap.banchango.recipe.domain.UserRecommendedRecipe;
import com.sundaegukbap.banchango.recipe.dto.event.RecommendedRecipeCategoryChangedEvent;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.recipe.repository.RecommendedRecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecommendedRecipeRepository recommendedRecipeRepository;
    private final ContainerIngredientRepository containerIngredientRepository;
    private final ContainerRepository containerRepository;
    private final AiRecipeRecommendClient aiRecipeRecommendClient;

    @EventListener
    public void refreshRecommendedRecipes(IngredientChangedEvent event) {
        refreshRecommendedRecipes(event.userId());
    }

    @EventListener
    public void refreshRecommendedRecipes(RecommendedRecipeCategoryChangedEvent event) {
        refreshRecommendedRecipes(event.userId());
    }

    @Transactional
    public void refreshRecommendedRecipes(Long userId) {
        List<Container> containers = containerRepository.findAllByUserId(userId);
        List<ContainerIngredient> containerIngredients = containerIngredientRepository.findByContainerIn(containers);
        List<Ingredient> ingredients = containerIngredients.stream()
                .map(ContainerIngredient::getIngredient)
                .collect(Collectors.toList());

        recommendedRecipeRepository.deleteAllByUserId(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        List<Long> recommendedRecipeIds = aiRecipeRecommendClient.getRecommendedRecipesFromAI(RecipeCategory.전체, ingredients);
        List<Recipe> recipes = recipeRepository.findAllById(recommendedRecipeIds);
        List<UserRecommendedRecipe> recommendedRecipes = recipes.stream()
                .map(recipe -> UserRecommendedRecipe.builder()
                        .user(user)
                        .recipe(recipe)
                        .build())
                .collect(Collectors.toList());
        recommendedRecipeRepository.saveAll(recommendedRecipes);
    }
}
