package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.OrderEntity;
import pizzeria.entity.ProductEntity;
import pizzeria.entity.UserEntity;
import pizzeria.model.Order;
import pizzeria.model.Product;
import pizzeria.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Order> getAllOrders() {
        return entityManager.createQuery("SELECT orderEntity FROM OrderEntity orderEntity", OrderEntity.class)
                .getResultList().stream().map(Order::fromEntity).toList();
    }

    public Optional<Order> getOrderById(int orderId) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, orderId);
        if (orderEntity == null) return Optional.empty();
        return Optional.of(Order.fromEntity(orderEntity));
    }

    /* public Optional<List<Order>> getAllOrdersByUserId(int userId) {
         UserEntity userEntity = entityManager.find(UserEntity.class, userId);
         if (userEntity == null) return Optional.empty();
         return Optional.of(entityManager.createQuery("SELECT orderEntity FROM OrderEntity orderEntity WHERE orderEntity.id LIKE :id_", OrderEntity.class)
                 .setParameter("id_", userId)
                 .getResultList().stream().map(Order::fromEntity).toList());
     }*/
//
//    public float calculateTotalCostOfOrder(int userId) {
//
//    }
    @Transactional
    public boolean save(Order order) {
        List<ProductEntity> products = entityManager.createQuery("SELECT productEntity FROM ProductEntity productEntity WHERE product_id IN :ids", ProductEntity.class)
                .setParameter("ids", order.getProducts().stream().map(Product::getId).toList()).getResultList();
        UserEntity user = entityManager.find(UserEntity.class, order.getUser());
        try {
            entityManager.persist(new OrderEntity(
                    products,
                    user,
                    order.isFinalized(),
                    order.getTotalCost()
            ));
            return true;
        } catch (PersistenceException e) {
            return false;

        }
    }

    @Transactional
    public void addOrder(Order order) {
        entityManager.persist(OrderEntity.fromOrder(order));
    }

    public Optional<List<Order>> getAllNotFinalizedOrders() {
        return Optional.empty();
    }
}
