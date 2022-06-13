package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzeria.model.Order;
import pizzeria.service.OrderService;

import java.time.LocalDateTime;

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
}
