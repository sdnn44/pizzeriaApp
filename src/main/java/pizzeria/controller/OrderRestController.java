package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pizzeria.model.Order;
import pizzeria.service.OrderService;

import java.util.List;

@Controller
//@RequestMapping("/api")
@RequiredArgsConstructor

public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        return orderService.getOrderBySpecificId(orderId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<Void> addOrder(@RequestBody Order order) {
//        return orderService.addOrder(order)
//                ? ResponseEntity.status(HttpStatus.CREATED).build()
//                : ResponseEntity.status(HttpStatus.CONFLICT).build();
//    }
}
