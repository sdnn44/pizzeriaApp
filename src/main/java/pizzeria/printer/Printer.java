package pizzeria.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pizzeria.repository.ProductRepository;
import pizzeria.service.IngredientService;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

@Component
@RequiredArgsConstructor
public class Printer {

    private final SizeService sizeService;
    private final ProductService productService;
    private final IngredientService ingredientService;

    public void process() {

        System.out.println("Jaki krakers wariacie?");
        System.out.println(sizeService.getAllSizes());
        System.out.println(productService.getAllProducts().get(0).getName());
        System.out.println(ingredientService.getAllIngredients());
    }
}
