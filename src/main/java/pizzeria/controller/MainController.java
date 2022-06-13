package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizzeria.model.Order;
import pizzeria.model.Product;
import pizzeria.model.Size;
import pizzeria.model.User;
import pizzeria.service.OrderService;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;
import pizzeria.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final SizeService sizeService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping()
    public String getMainPage(Model model){
        List<Size> allSizes = sizeService.getAllSizes();
        model.addAttribute("sizes",allSizes);
        allSizes.forEach(size -> model.addAttribute("id_"+size.getId(),productService.getProductsBySize(size.getId()))
        );

        return "productPanel.html";
    }

//    @GetMapping()
//    public String getShoppingCardDetails(Model model) {
//        List<User> allUsers = userService.getAllUsers();
//        List<Order> allOrders = orderService.getAllOrders();
////        List<Product> allProducts = productService.getAllProducts();
//        model.addAttribute("users", allUsers);
//        model.addAttribute("orders", allOrders);
//        allOrders.forEach(order -> model.addAttribute(order., orderService.getOrderByUserId(user.getId())));
//        return "shopping_card-test.html";
//    }
}
