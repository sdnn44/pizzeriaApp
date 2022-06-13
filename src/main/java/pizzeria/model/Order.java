package pizzeria.model;

import lombok.*;
import pizzeria.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

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
    private  List<Product> products;

    public static Order fromEntity(OrderEntity orderEntity){
        return new Order(
                orderEntity.getAddress(),
                orderEntity.getOrderDate(),
                orderEntity.getTotal(),
                orderEntity.getProducts().stream().map(Product::fromEntity).toList()
        );
    }

    public Order(List<Product> products) {
        this.address = null;
        this.products = products;
        this.orderDate = null;
        this.total = products.stream().map(Product::getPrice).reduce(0f, Float::sum);
    }
}
