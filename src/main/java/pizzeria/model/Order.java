package pizzeria.model;

import lombok.*;
import pizzeria.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {

    private  String address;
    private  LocalDateTime orderDate;
    private  float total;
    private  List<OrderItem> orderItems;

    public static Order fromEntity(OrderEntity orderEntity){
        return new Order(
                orderEntity.getAddress(),
                orderEntity.getOrderDate(),
                orderEntity.getTotal(),
                orderEntity.getOrderItems().stream().map(OrderItem::fromEntity).toList()
        );
    }


    public Order(List<OrderItem> orderItems){
        this.address = null;
        this.orderItems = orderItems;
        this.orderDate = null;
        this.total = orderItems.stream()
                .map(orderItem -> (orderItem.getProduct().getPrice()*orderItem.getQuantity()))
                .reduce(0f,Float::sum);
    }
}
