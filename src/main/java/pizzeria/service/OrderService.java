package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import pizzeria.model.Order;
import pizzeria.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
    public boolean addOrder(Order order) {
        return orderRepository.save(order);
    }
//    public Optional<List<Order>> getAllOrdersByUserId(int userId) {
//        return orderRepository.getAllOrdersByUserId(userId);
//    }

    public Optional<Order> getOrderBySpecificId(int id) {
        return orderRepository.getOrderById(id);
    }
    public Optional<List<Order>> getAllNotFinalizedOrders() {
        return orderRepository.getAllNotFinalizedOrders();
    }
    public List<Order> getOrderByUserId(int userId) {
        return orderRepository
                .getAllOrders()
                .stream()
                .filter(id -> id.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

//    public float calucalteTotalCostOfOrder(int userId) {
//        List<Order> allOrders = getOrderById(id_);
//        if (allOrders.isEmpty())
//            return 0f;
//        float totalCost = allOrders
//    }
}
