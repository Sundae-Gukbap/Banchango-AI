package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.bookmark.repository.RecipeBookmarkRepository;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.ingredient.domain.RecipeRequiringIngredient;
import com.sundaegukbap.banchango.ingredient.domain.UserHavingIngredient;
import com.sundaegukbap.banchango.ingredient.repository.RecipeRequiringIngredientRepository;
import com.sundaegukbap.banchango.ingredient.repository.UserHavingIngredientRepository;
import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserHavingIngredientRepository userHavingIngredientRepository;
    @Mock
    private RecipeRequiringIngredientRepository recipeRequiringIngredientRepository;
    @InjectMocks
    private RecipeService recipeService;

    User user;
    Ingredient ingredient1, ingredient2;
    Recipe recipe;
    RecipeBookmark recipeBookmark;
    UserHavingIngredient userHavingIngredient;
    RecipeRequiringIngredient recipeRequiringIngredient1, recipeRequiringIngredient2;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .id(1L)
                .build();

        recipe = Recipe.builder()
                .id(1L)
                .name("간장계란밥")
                .introduction("맛있습니다.")
                .image("image.png")
                .link("link")
                .servings(4)
                .cookingTime(30)
                .difficulty(Difficulty.초급)
                .build();

        recipeBookmark = RecipeBookmark.builder()
                .id(1L)
                .user(user)
                .recipe(recipe)
                .build();

        ingredient1 = Ingredient.builder()
                .id(1L)
                .name("김치")
                .kind("채소")
                .build();
        ingredient2 = Ingredient.builder()
                .id(2L)
                .name("밥")
                .kind("밥")
                .build();

        recipeRequiringIngredient1 = RecipeRequiringIngredient.builder()
                .id(1L)
                .recipe(recipe)
                .ingredient(ingredient1)
                .build();
        recipeRequiringIngredient2 = RecipeRequiringIngredient.builder()
                .id(2L)
                .recipe(recipe)
                .ingredient(ingredient2)
                .build();

        userHavingIngredient = UserHavingIngredient.builder()
                .id(1L)
                .user(user)
                .ingredient(ingredient1)
                .build();
    }

    @Nested
    @DisplayName("recipeServiceQueryService")
    class 레시피_조회 {
        @Test
        @DisplayName("recipeService.getRecipe()")
        void 단일_레시피_상세정보_조회() {
            //given
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
            when(userHavingIngredientRepository.findAllByUser(user)).thenReturn(new ArrayList<>(List.of(userHavingIngredient)));
            when(recipeRequiringIngredientRepository.findAllByRecipe(recipe)).thenReturn(new ArrayList<>(List.of(recipeRequiringIngredient1, recipeRequiringIngredient2)));
            RecipeDetailResponse expected = RecipeDetailResponse.of(
                    recipe,
                    new ArrayList<>(List.of(
                            "김치"
                    )),
                    new ArrayList<>(List.of(
                            "밥"
                    ))
            );

            //when
            RecipeDetailResponse result = recipeService.getRecipe(1L, 1L);

            //then
            assertThat(result).isEqualTo(expected);
        }
    }
}
