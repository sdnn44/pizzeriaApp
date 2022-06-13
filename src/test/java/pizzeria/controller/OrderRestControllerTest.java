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
import pizzeria.model.*;
import pizzeria.service.IngredientService;
import pizzeria.service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    OrderService orderService;

    @Autowired
    private JacksonTester<List<Order>> jacksonOrder;

    private final List<Order> orders = List.of(
            new Order(
                    "Warszawa Wodna 20",
                    LocalDateTime.of(2022,12,10,0,0),
                    40.89f,
                    List.of(
                            new OrderItem(
                                    1,2, new Product(1,
                                    "Pizza1",
                                    List.of(
                                            new Ingredient(1, "Sos", true, null),
                                            new Ingredient(2, "Ser", true, "laktoza")),
                                    new Size(1, "Mala", 32),
                                    21.10f
                            ))
                    )
            ),
            new Order(
                    "Lublin Perlowa 10",
                    LocalDateTime.of(2022,1,1,0,0),
                    30.30f,
                    List.of(
                            new OrderItem(
                                    1,3, new Product(1,
                                    "Pizza2",
                                    List.of(
                                            new Ingredient(1, "Sos", true, null),
                                            new Ingredient(2, "Ser", true, "laktoza")),
                                    new Size(1, "Mala", 32),
                                    21.10f
                            ))
                    )
            )
    );

    @Test
    void should_return_all_ingredients() throws Exception{
        //given
        Mockito.when(orderService.getAllOrders()).thenReturn(orders);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/orders").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonOrder.write(orders).getJson()
        );
    }

    @Test
    void should_return_orders_by_date() throws Exception{
        //given
        Mockito.when(orderService.getOrdersByDate(Mockito.any(LocalDate.class))).thenReturn(List.of(orders.get(0)));

        //when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/orders/date?day=1&month=1&year=1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                jacksonOrder.write(List.of(orders.get(0))).getJson()
        );
    }


}