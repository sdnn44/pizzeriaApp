package pizzeria.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import pizzeria.model.Ingredient;
import pizzeria.service.IngredientService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(IngredientRestController.class)
class IngredientRestControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockBean
    IngredientService ingredientService;

    @Autowired
    private JacksonTester<List<Ingredient>> jacksonIngredient;

    @Test
    void should_return_all_ingredients() throws Exception{
        //given
        Mockito.when(ingredientService.getAllIngredients()).thenReturn(List.of(
                new Ingredient(1, "Sos", true, null),
                new Ingredient(2,"Ser",true,"laktoza")
        ));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/ingredients").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonIngredient.write(List.of(
                        new Ingredient(1, "Sos", true, null),
                        new Ingredient(2,"Ser",true,"laktoza")
                )).getJson()
        );
    }


}