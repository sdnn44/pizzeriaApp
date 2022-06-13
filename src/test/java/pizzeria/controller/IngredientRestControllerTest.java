package pizzeria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pizzeria.model.Ingredient;
import pizzeria.repository.IngredientRepository;
import pizzeria.service.IngredientService;

import java.net.http.HttpRequest;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

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