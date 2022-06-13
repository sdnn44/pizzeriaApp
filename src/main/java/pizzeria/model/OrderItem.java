package pizzeria.model;

import lombok.*;
import pizzeria.entity.OrderItemEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderItem {
    private int id;
    private int quantity;
    private Product product;

    public static OrderItem fromEntity(OrderItemEntity orderItemEntity){
        return new OrderItem(orderItemEntity.getId(), orderItemEntity.getQuantity(), Product.fromEntity(orderItemEntity.getProduct()));
    }
}
