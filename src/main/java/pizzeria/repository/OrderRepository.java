package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import pizzeria.entity.*;
import pizzeria.model.Ingredient;
import pizzeria.model.Order;
import pizzeria.model.OrderItem;
import pizzeria.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Order> getAllOrders(){
        return entityManager.createQuery("SELECT orderEntity FROM OrderEntity orderEntity", OrderEntity.class)
                .getResultList()
                .stream()
                .map(Order::fromEntity)
                .toList();
    }

    @Transactional
    public boolean save(Order order){
        OrderEntity orderEntity = new OrderEntity(
                order.getAddress(),
                order.getOrderDate(),
                order.getTotal(),
                new ArrayList<>()
        );
        order.getOrderItems().forEach(orderItem -> {
            orderEntity.getOrderItems().add(new OrderItemEntity(orderItem.getQuantity(),entityManager.find(ProductEntity.class,orderItem.getProduct().getId())));
        });
        try {
            entityManager.persist(
                    orderEntity
            );
            return true;
        }catch (PersistenceException e){
            return false;
        }
    }
}
