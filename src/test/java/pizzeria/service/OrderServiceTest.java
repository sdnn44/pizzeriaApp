package pizzeria.service;

import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.*;
import pizzeria.repository.OrderRepository;
import pizzeria.repository.ProductRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

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
    void should_return_all_orders(){
        //given
        Mockito.when(orderRepository.getAllOrders()).thenReturn(orders);

        //when
        List<Order> result = orderService.getAllOrders();

        //then
        Assertions.assertThat(result.size()).isEqualTo(orders.size());
        Assertions.assertThat(result.get(0).getAddress()).isEqualTo(orders.get(0).getAddress());
        Assertions.assertThat(result.get(1).getTotal()).isEqualTo(orders.get(1).getTotal());
        Assertions.assertThat(result.get(1).getOrderItems().size()).isEqualTo(result.get(1).getOrderItems().size());
    }

    @Test
    void should_return_true_when_saved_order(){
        //given
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(true);

        //when
        boolean result = orderService.save(
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

        //then
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void should_return_orders_when_given_date(){
        //given
        Mockito.when(orderRepository.getAllOrders()).thenReturn(orders);
        LocalDate date = LocalDate.of(2022,12,10);

        //when
        List<Order> result = orderService.getOrdersByDate(date);

        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getOrderDate().getYear()).isEqualTo(date.getYear());
        Assertions.assertThat(result.get(0).getOrderDate().getMonth()).isEqualTo(date.getMonth());
        Assertions.assertThat(result.get(0).getOrderDate().getDayOfMonth()).isEqualTo(date.getDayOfMonth());
        Assertions.assertThat(result.get(0).getAddress()).isEqualTo(orders.get(0).getAddress());
    }
}