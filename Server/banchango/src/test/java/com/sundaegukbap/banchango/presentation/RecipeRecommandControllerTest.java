package com.sundaegukbap.banchango.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundaegukbap.banchango.recipe.application.RecipeRecommandService;
import com.sundaegukbap.banchango.recipe.domain.Difficulty;
import com.sundaegukbap.banchango.recipe.dto.RecipeDetailResponse;
import com.sundaegukbap.banchango.recipe.presentation.RecipeRecommandController;
import com.sundaegukbap.banchango.support.CustomWebMvcTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@CustomWebMvcTest(RecipeRecommandController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RecipeRecommandControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    RecipeRecommandService recipeRecommandService;

    @Test
    void 추천_레시피_조회() throws Exception {
        //given

        List<RecipeDetailResponse> expected = new ArrayList<>();
        List<String> have = new ArrayList<>();
        have.add("간장");
        have.add("밥");
        List<String> need = new ArrayList<>();
        need.add("계란");
        need.add("당근");
        RecipeDetailResponse recipeDetail = new RecipeDetailResponse(1L, "간장계란볶음밥", "달짝지근함", "test.png", "test.link", have, need, 1, 30, Difficulty.아무나);
        expected.add(recipeDetail);
        given(recipeRecommandService.getRecipes(anyLong()))
                .willReturn(expected);

        // when & then
        String content = mockMvc.perform(get("/recipe/recommand/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        List<RecipeDetailResponse> actual = objectMapper.readValue(content,  new TypeReference<List<RecipeDetailResponse>>(){});

        assertThat(actual).isEqualTo(expected);
    }
}