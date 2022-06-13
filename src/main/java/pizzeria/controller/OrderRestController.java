package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzeria.model.Order;
import pizzeria.service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public String placeOrder(Order order){
        System.out.println(order);
        order.setOrderDate(LocalDateTime.now());
        boolean result = orderService.save(order);
        return result?"orderPlaced.html":"orderPlacingError.html";
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());

    }

    @GetMapping("/orders/date")
    public ResponseEntity<List<Order>> getOrdersByDate(@RequestParam int day,@RequestParam int month,@RequestParam int year){
        List<Order> orders = orderService.getOrdersByDate( LocalDate.of(year,month,day));
        if(orders.size() == 0) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(orders);
    }


}
