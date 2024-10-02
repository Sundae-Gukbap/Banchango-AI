//package com.sundaegukbap.banchango.recipe.application;
//
//import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
//import com.sundaegukbap.banchango.bookmark.repository.RecipeBookmarkRepository;
//import com.sundaegukbap.banchango.ingredient.application.IngredientMatcher;
//import com.sundaegukbap.banchango.recipe.domain.Recipe;
//import com.sundaegukbap.banchango.recipe.domain.RecipeDifficulty;
//import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponse;
//import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponses;
//import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
//import com.sundaegukbap.banchango.user.domain.User;
//import com.sundaegukbap.banchango.user.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@SuppressWarnings("NonAsciiCharacters")
//@ExtendWith(MockitoExtension.class)
//public class RecipeServiceTest {
//    @Mock
//    private RecipeRepository recipeRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private IngredientMatcher ingredientMatcher;
//    @Mock
//    private RecipeBookmarkRepository recipeBookmarkRepository;
//    @InjectMocks
//    @Spy
//    private RecipeService recipeService;
//    @InjectMocks
//    @Spy
//    private RecipeQueryService recipeQueryService;
//
//    User user;
//    Recipe recipe;
//    RecipeBookmark recipeBookmark;
//    List<String> have,need;
//    HashMap<String,List> ingredientRelation;
//    RecommendedRecipeResponse recommendedRecipeResponse;
//
//    @BeforeEach
//    void setUp(){
//        user = User.builder()
//                .id(1L)
//                .build();
//
//        recipe = Recipe.builder()
//                .id(1L)
//                .name("간장계란밥")
//                .introduction("맛있습니다.")
//                .image1("image.png")
//                .link("link")
//                .servings(4)
//                .cookingTime(30)
//                .recipeDifficulty(RecipeDifficulty.초급)
//                .build();
//
//        recipeBookmark = RecipeBookmark.builder()
//                .id(1L)
//                .user(user)
//                .recipe(recipe)
//                .build();
//
//        have = new ArrayList<>(List.of(
//                "김치"
//        ));
//
//        need = new ArrayList<>(List.of(
//                "밥"
//        ));
//
//        ingredientRelation = new HashMap<>(Map.of(
//                "have", have,
//                "need", need
//        ));
//
//        recommendedRecipeResponse = RecommendedRecipeResponse.of(
//                recipe,
//                null,
//                null
//        );
//    }
//
//    @Nested
//    @DisplayName("recipeServiceQueryService")
//    class 레시피_조회 {
//        @Test
//        @DisplayName("recipeService.getRecipe()")
//        void 단일_레시피_상세정보_조회() {
//            //given
//            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//            when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
//            when(ingredientMatcher.checkIngredientRelation(user,recipe)).thenReturn(ingredientRelation);
//            RecommendedRecipeResponse expected = recommendedRecipeResponse;
//
//            //when
//            RecommendedRecipeResponse result = recipeQueryService.getRecipeDetail(1L, 1L);
//
//            //then
//            assertThat(result).isEqualTo(expected);
//        }
//
//        @Test
//        @DisplayName("recipeService.getRecommendedRecipes()")
//        void 추천_레시피_목록_조회() {
//            //given
//            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//            when(recipeBookmarkRepository.findAllByUser(user)).thenReturn(Arrays.asList(recipeBookmark));
//            doReturn(recommendedRecipeResponse).when(recipeQueryService).getRecipeDetail(user.getId(),recipe.getId());
//            List<RecommendedRecipeResponse> expected = Arrays.asList(recommendedRecipeResponse);
//
//            //when
//            RecommendedRecipeResponses result = recipeQueryService.getRecommendedRecipes(user.getId());
//
//            //then
//            assertThat(result).isEqualTo(expected);
//        }
//    }
//}
