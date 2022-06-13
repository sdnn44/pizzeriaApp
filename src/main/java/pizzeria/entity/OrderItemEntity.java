package pizzeria.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="order_items",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public OrderItemEntity(int quantity, ProductEntity product) {
        this.quantity = quantity;
        this.product = product;
    }
}
