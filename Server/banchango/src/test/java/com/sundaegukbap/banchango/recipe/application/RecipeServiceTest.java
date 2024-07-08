package com.sundaegukbap.banchango.recipe.application;

import com.sundaegukbap.banchango.bookmark.domain.RecipeBookmark;
import com.sundaegukbap.banchango.ingredient.domain.Ingredient;
import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.domain.Recipe;
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
import java.util.Set;

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
    @BeforeEach
    void setUp(){
        User user = User.builder()
                .id(1L)
                .build();

        Recipe recipe = Recipe.builder()
                .id(1L)
                .name("간장계란밥")
                .introduction("맛있습니다.")
                .image("image.png")
                .link("link")
                .servings(4)
                .cookingTime(30)
                .difficulty(Difficulty.초급)
                .build();

        RecipeBookmark recipeBookmark = RecipeBookmark.builder()
                .id(1L)
                .user(user)
                .recipe(recipe)
                .build();
    }

    @Test
    void 단일_레시피_상세정보_조회(){
        //given

        //when
        /*
        특정 서비스 메서드를 수행하였을때 리포지토리에서 특정객체를 반환했다면
         */

        //then
    }
}
