package pizzeria.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders",schema = "public")
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


    private String address;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total")
    private float total;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<ProductEntity> products;

    public OrderEntity(String address, LocalDateTime orderDate, float total, List<ProductEntity> products) {
        this.address = address;
        this.orderDate = orderDate;
        this.total = total;
        this.products = products;
    }
}
