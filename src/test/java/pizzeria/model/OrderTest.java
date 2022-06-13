package pizzeria.model;

import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzeria.entity.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void should_create_order_given_order_entity() {
        //given
        OrderEntity orderEntity = new OrderEntity("Lublin Perlowa 10",
                LocalDateTime.of(2022,1,1,0,0),
                30.30f,
                List.of(
                        new OrderItemEntity(
                                1,3, new ProductEntity(1,
                                "Pizza2",
                                List.of(
                                        new IngredientEntity(1, "Sos", true, null),
                                        new IngredientEntity(2, "Ser", true, "laktoza")),
                                new SizeEntity(1, "Mala", 32),
                                21.10f
                        ))
                )
        );

        //when
        Order order = Order.fromEntity(orderEntity);

        //then
        Assertions.assertThat(order.getAddress()).isEqualTo(orderEntity.getAddress());
        Assertions.assertThat(order.getTotal()).isEqualTo(orderEntity.getTotal());
        Assertions.assertThat(order.getOrderDate()).isEqualTo(orderEntity.getOrderDate());
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(orderEntity.getOrderItems().size());

    }

    @Test
    void should_create_order_with_correct_total_when_give_products(){
        //given
        List<OrderItem> orderItems = List.of(
                new OrderItem(
                        1,2, new Product(1,
                        "Pizza1",
                        List.of(
                                new Ingredient(1, "Sos", true, null),
                                new Ingredient(2, "Ser", true, "laktoza")),
                        new Size(1, "Mala", 32),
                        21.10f
                )),
                new OrderItem(
                        2,2, new Product(1,
                        "Pizza1",
                        List.of(
                                new Ingredient(1, "Sos", true, null),
                                new Ingredient(2, "Ser", true, "laktoza")),
                        new Size(1, "Mala", 32),
                        11.10f
                ))
        );

        //when
        Order order = new Order(orderItems);

        //then
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(2);
        Assertions.assertThat(order.getTotal()).isEqualTo(64.40f);
    }

}