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
import pizzeria.model.Product;
import pizzeria.model.Size;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    ProductService productService;

    @Autowired
    private JacksonTester<List<Product>> jacksonProductList;

    @Autowired
    private JacksonTester<Product> jacksonProduct;

    @Test
    void should_return_all_products() throws Exception{
        //given
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(
                new Product(1,
                        "Pizza1",
                        List.of(
                                new Ingredient(1, "Sos", true, null),
                                new Ingredient(2, "Ser", true, "laktoza")),
                        new Size(1, "Mala", 32),
                        21.10f
                ),
                new Product(2,
                        "Pizza2",
                        List.of(
                                new Ingredient(1, "Sos", true, null),
                                new Ingredient(2, "Ser", true, "laktoza"),
                                new Ingredient(3, "Szynka", false, null)
                        ),
                        new Size(2, "Duza", 42),
                        32.10f
                )
        ));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/products").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonProductList.write(List.of(
                        new Product(1,
                                "Pizza1",
                                List.of(
                                        new Ingredient(1, "Sos", true, null),
                                        new Ingredient(2, "Ser", true, "laktoza")),
                                new Size(1, "Mala", 32),
                                21.10f
                        ),
                        new Product(2,
                                "Pizza2",
                                List.of(
                                        new Ingredient(1, "Sos", true, null),
                                        new Ingredient(2, "Ser", true, "laktoza"),
                                        new Ingredient(3, "Szynka", false, null)
                                ),
                                new Size(2, "Duza", 42),
                                32.10f
                        )
                )).getJson()
        );
    }

    @Test
    void should_return_product_when_given_id() throws Exception{
        //given
        Mockito.when(productService.getProductById(1)).thenReturn(
                java.util.Optional.of(new Product(1,
                        "Pizza1",
                        List.of(
                                new Ingredient(1, "Sos", true, null),
                                new Ingredient(2, "Ser", true, "laktoza")),
                        new Size(1, "Mala", 32),
                        21.10f))
        );

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/products/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonProduct.write(
                        new Product(1,
                                "Pizza1",
                                List.of(
                                        new Ingredient(1, "Sos", true, null),
                                        new Ingredient(2, "Ser", true, "laktoza")),
                                new Size(1, "Mala", 32),
                                21.10f
                        )).getJson()
        );
    }

    @Test
    void should_return_not_found_when_given_incorrect_id() throws Exception{
        //given
        Mockito.when(productService.getProductById(1)).thenReturn(
                Optional.empty()
        );

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/products/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void should_return_created_when_saved_product() throws Exception{
        //given
        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(true);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                jacksonProduct.write(new Product(1,
                                        "Pizza1",
                                        List.of(
                                                new Ingredient(1, "Sos", true, null),
                                                new Ingredient(2, "Ser", true, "laktoza")),
                                        new Size(1, "Mala", 32),
                                        21.10f
                                )).getJson()
                        ))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

}