package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final SizeService sizeService;

    @GetMapping
    public String getMainPage(Model model){
        model.addAttribute("sizes",sizeService.getAllSizes());
        model.addAttribute("products",productService.getAllProducts());
        return "productPanel.html";
    }
}
