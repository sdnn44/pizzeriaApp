package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzeria.model.Order;
import pizzeria.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public boolean save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByDate(LocalDate localDate){
        return orderRepository.getAllOrders().stream().filter(order -> {
                    return order.getOrderDate().getYear() == localDate.getYear() &&
                            order.getOrderDate().getMonth() == localDate.getMonth() &&
                            order.getOrderDate().getDayOfMonth() == localDate.getDayOfMonth();
                }).toList();
    }


}
