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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> orderItems;


    public OrderEntity(String address, LocalDateTime orderDate, float total, List<OrderItemEntity> orderItems) {
        this.address = address;
        this.orderDate = orderDate;
        this.total = total;
        this.orderItems = orderItems;
    }
}
