package pizzeria.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.*;
import pizzeria.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderService orderService;

    private final List<Order> mockOrders = List.of(
            new Order(1, List.of(
                        new Product(1,
                                "Pizza1",
                                List.of(
                                        new Ingredient(1, "Sos", true, null),
                                        new Ingredient(2, "Ser", true, "laktoza")),
                                new Size(1, "Mała", 32),
                                21.10f
                        )), new User(1, "John", "Nash", "jnash", "jn@gmail.com", "zxc", "zxc",
                        new Address(1, "Perlowa", "5", "42-320", "Lublin")), false, 60.00f),
            new Order(2, List.of(
                    new Product(2,
                            "Pizza2",
                            List.of(
                                    new Ingredient(1, "Sos", true, null),
                                    new Ingredient(2, "Ser", true, "laktoza")),
                            new Size(1, "Mała", 32),
                            21.10f
                    )), new User(2, "John", "Nash", "jnash", "jn@gmail.com", "zxc", "zxc",
                    new Address(2, "Perlowa", "5", "42-320", "Lublin")), false, 30.00f
            ),
            new Order(3, List.of(
                    new Product(3,
                            "Pizza2.1",
                            List.of(
                                    new Ingredient(1, "Sos", true, null),
                                    new Ingredient(2, "Ser", true, "laktoza")),
                            new Size(1, "Mała", 32),
                            21.10f
                    )), new User(2, "John", "Nash", "jnash", "jn@gmail.com", "zxc", "zxc",
                    new Address(2, "Perlowa", "5", "42-320", "Lublin")), false, 30.00f
            )

    );

    @Test
    void should_return_all_orders() {
        Mockito.when(orderRepository.getAllOrders()).thenReturn(mockOrders);

        //when
        List<Order> result = orderService.getAllOrders();

        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void should_return_specific_order_given_id() {
        Mockito.when(orderRepository.getOrderById(1)).thenReturn(Optional.ofNullable(mockOrders.get(0)));

        //when
        Order result = orderService.getOrderBySpecificId(1).orElse(null);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getProducts().get(0).getName()).isEqualTo("Pizza1");
    }

    @Test
    void should_return_null_when_specific_order_given_id_does_not_exist() {
        Mockito.when(orderRepository.getOrderById(6)).thenReturn(Optional.empty());

        //when
        Order result = orderService.getOrderBySpecificId(6).orElse(null);

        //then
        Assertions.assertThat(result).isNull();
    }

    @Test
    void should_return_all_orders_ordered_by_single_user() {
        Mockito.when(orderService.getOrderByUserId(2)).thenReturn(mockOrders);

        //when
        List<Order> result = orderService.getOrderByUserId(2);

        //then
        Assertions.assertThat(result.get(1).getProducts().get(0).getName()).isEqualTo("Pizza2.1");
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void should_return_true_when_added_order() {
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(true);

        //when
        boolean result = orderService.addOrder(
                new Order(
                        3, List.of(new Product(2, "PizzaT", List.of(new Ingredient(1, "sos", true, null)), new Size(2, "Duża", 42), 32.10f)),
                        new User(
                                1, "Lukasz", "Kowalski", "lkowal", "lkowal@wp.pl", "kowal123", "kowal123", new Address(1, "Perlowa", "8", "33-333", "Lublin")
                        ), false, 60.5f)
        );

        Assertions.assertThat(result).isTrue();
    }
}