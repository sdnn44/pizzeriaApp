package pizzeria.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pizzeria.model.*;
import pizzeria.service.OrderService;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("order")
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final SizeService sizeService;
    private final OrderService orderService;

    @GetMapping
    public String getMainPage(Model model){
        List<Size> allSizes = sizeService.getAllSizes();
        model.addAttribute("sizes",allSizes);
        allSizes.forEach(size -> model.addAttribute("test"+size.getId(),productService.getProductsBySize(size.getId()))
        );

        return "productPanel.html";
    }



    @GetMapping("/shopping-cart")
    public String getShoppingCartPage(@RequestParam List<Integer> productIds,Model model){
        List<OrderItem> orderItems = new ArrayList<>();
        productIds.forEach(id ->{
            OrderItem item = orderItems.stream().filter(orderItem -> orderItem.getId() == id).findFirst().orElse(null);
            if(item != null) item.setQuantity(item.getQuantity()+1);
            else {
                Product product = productService.getProductById(id).orElse(null);
                if(product!=null) orderItems.add(new OrderItem(id,1,product));

            }
        });
        model.addAttribute("address",new Address());
        model.addAttribute("order",new Order(orderItems));
        System.out.println(model.getAttribute("order"));
        return "shoppingCart.html";
    }

    @PostMapping("/orders")
    public String placeOrder(Address address,@ModelAttribute("order") Order order){

        order.setOrderDate(LocalDateTime.now());
        order.setAddress(address.toString());
        System.out.println(order);
        boolean result = orderService.save(order);
        return result?"orderPlaced.html":"orderPlacingError.html";
    }






}
