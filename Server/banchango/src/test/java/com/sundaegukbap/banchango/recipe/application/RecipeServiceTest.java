package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.repository.RecipeRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
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
    @InjectMocks
    private RecipeService recipeService;

    User user;
    Recipe recipe;
    RecipeBookmark recipeBookmark;

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
    }

    @Test
    void 단일_레시피_상세정보_조회(){
        //given
        RecipeDetailResponse expected = RecipeDetailResponse.of(
                recipe,
                new ArrayList<>(List.of(
                        "김치"
                )),
                new ArrayList<>(List.of(
                        "밥"
                ))
        );
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        //when
        RecipeDetailResponse result = recipeService.getRecipe(1L, 1L);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
