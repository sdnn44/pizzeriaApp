package pizzeria.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pizzeria.entity.OrderEntity;

import java.util.List;

@RequiredArgsConstructor
@Getter

public class Order {
    private final int id;
    private final List<Product> products;
    private final User user;
    private final boolean isFinalized;
    private final float totalCost;

    public static Order fromEntity(OrderEntity order) {
        return new Order(
                order.getId(),
                order.getProducts().stream().map(Product::fromEntity).toList(),
                User.fromEntity(order.getUser()),
                order.isFinalized(),
                order.getTotalCost()
        );
    }
}
