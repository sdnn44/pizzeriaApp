package pizzeria.entity;

import lombok.*;
import pizzeria.model.Order;
import pizzeria.model.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_finalized")
    private boolean isFinalized;

    @Column(name = "total")
    private float totalCost;

//    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")}
    )
    private List<ProductEntity> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public OrderEntity(List<ProductEntity> products, UserEntity user, boolean isFinalized, float totalCost) {
        this.products = products;
        this.user = user;
        this.isFinalized = isFinalized;
        this.totalCost = totalCost;
    }

    public static OrderEntity fromOrder(Order order) {
        return null;//new OrderEntity(order.getProducts().stream().map(Product::fromEntity), order.getId(), order.isFinalized(), order.getTotalCost())
    }
}
