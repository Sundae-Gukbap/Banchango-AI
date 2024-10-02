//package com.sundaegukbap.banchango.recipe.presentation;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sundaegukbap.banchango.recipe.application.RecipeQueryService;
//import com.sundaegukbap.banchango.recipe.application.RecipeService;
//import com.sundaegukbap.banchango.recipe.domain.RecipeDifficulty;
//import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponse;
//import com.sundaegukbap.banchango.recipe.dto.response.RecommendedRecipeResponses;
//import com.sundaegukbap.banchango.support.CustomWebMvcTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@CustomWebMvcTest
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@SuppressWarnings("NonAsciiCharacters")
//public class RecipeControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    RecipeService recipeService;
//
//    @MockBean
//    RecipeQueryService recipeQueryService;
//
//    RecommendedRecipeResponse recommendedRecipeResponse;
//
//    @BeforeEach
//    void setUp() {
//        recommendedRecipeResponse =
//                new RecommendedRecipeResponse(
//                        1L,
//                        "간장계란볶음밥",
//                        "달짝지근함",
//                        "test.png",
//                        "test.link",
//                        new ArrayList<>(
//                                List.of("간장", "밥")
//                        ),
//                        new ArrayList<>(
//                                List.of("계란", "당근")
//                        ),
//                        1,
//                        30,
//                        RecipeDifficulty.아무나
//                );
//    }
//
//    @Nested
//    @DisplayName("/api/recipe/")
//    class 레시피_조회 {
//        @Test
//        @DisplayName("/recommend/{userId}")
//        void 추천_레시피_조회() throws Exception {
//            //given
//            RecommendedRecipeResponses expected = RecommendedRecipeResponses.of(recommendedRecipeResponse);
//
//            given(recipeQueryService.getRecommendedRecipes(anyLong()))
//                    .willReturn(expected);
//
//            // when
//            String content = mockMvc.perform(get("/api/recipe/recommend/{userId}", 1L)
//                            .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andDo(print())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString(StandardCharsets.UTF_8);
//            List<RecommendedRecipeResponse> actual = objectMapper.readValue(content, new TypeReference<List<RecommendedRecipeResponse>>() {
//            });
//
//            //then
//            assertThat(actual).isEqualTo(expected);
//        }
//
//        @Test
//        @DisplayName("/{userId}/{recipeId}")
//        void 상세_레시피_조회() throws Exception {
//            //given
//            RecommendedRecipeResponse expected = recommendedRecipeResponse;
//
//            given(recipeService.getRecipe(anyLong(), anyLong()))
//                    .willReturn(expected);
//
//            // when
//            String content = mockMvc.perform(get("/api/recipe/{userId}/{recipeId}", 1L, 1L)
//                            .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andDo(print())
//                    .andReturn()
//                    .getResponse()
//                    .getContentAsString(StandardCharsets.UTF_8);
//            RecommendedRecipeResponse actual = objectMapper.readValue(content, new TypeReference<RecommendedRecipeResponse>() {
//            });
//
//            //then
//            assertThat(actual).isEqualTo(expected);
//        }
//    }
//}
