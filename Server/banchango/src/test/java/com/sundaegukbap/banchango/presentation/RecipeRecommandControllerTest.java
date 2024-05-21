package com.sundaegukbap.banchango.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundaegukbap.banchango.recipe.application.RecipeRecommandService;
import com.sundaegukbap.banchango.support.CustomWebMvcTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        // given

//        List<RecipeDetailResponse> expected = new ArrayList<>();
//        FestivalDetailResponse expected = new FestivalDetailResponse(1L, 1L, "테코 대학교", LocalDate.now(), LocalDate.now(),
//                "thumbnail.png", Collections.emptyList());
//
//        given(festivalService.findDetail(anyLong()))
//                .willReturn(expected);
//
//        // when & then
//        String content = mockMvc.perform(get("/festivals/{festivalId}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn()
//                .getResponse()
//                .getContentAsString(StandardCharsets.UTF_8);
//        FestivalDetailResponse actual = objectMapper.readValue(content, FestivalDetailResponse.class);
//        assertThat(actual).isEqualTo(expected);
    }
}