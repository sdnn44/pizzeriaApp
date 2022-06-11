package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pizzeria.model.Size;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final SizeService sizeService;

    @GetMapping
    public String getMainPage(Model model){
        List<Size> allSizes = sizeService.getAllSizes();
        model.addAttribute("sizes",allSizes);
        allSizes.forEach(size -> model.addAttribute(size.getName(),productService.getProductsBySize(size.getId()))
        );

        return "productPanel.html";
    }
}
