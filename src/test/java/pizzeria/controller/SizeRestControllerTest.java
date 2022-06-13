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
import pizzeria.model.Size;
import pizzeria.service.IngredientService;
import pizzeria.service.SizeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(SizeRestController.class)
class SizeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockBean
    SizeService sizeService;

    @Autowired
    private JacksonTester<List<Size>> jacksonSize;

    @Test
    void should_return_all_sizes() throws Exception{
        //given
        Mockito.when(sizeService.getAllSizes()).thenReturn(List.of(
                new Size(1,"Mala",32),
                new Size(2,"Duza",42)
        ));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/sizes").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonSize.write(List.of(
                        new Size(1,"Mala",32),
                        new Size(2,"Duza",42)
                )).getJson()
        );
    }
}