package pizzeria.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pizzeria.model.Ingredient;
import pizzeria.model.Product;
import pizzeria.repository.ProductRepository;
import pizzeria.service.IngredientService;
import pizzeria.service.ProductService;
import pizzeria.service.SizeService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Printer {

    private final SizeService sizeService;
    private final ProductService productService;
    private final IngredientService ingredientService;

    public void process() {

        System.out.println("Jaki krakers wariacie?");
        System.out.println(sizeService.getAllSizes());
        ingredientService.addIngredient(new Ingredient(4,"Prosciutto Cotto",false,null));
        System.out.println(ingredientService.getAllIngredients());
        productService.addProduct(
                new Product(
                        100,
                        "Cotto",
                        List.of(ingredientService.getIngredientById(1),
                                ingredientService.getIngredientById(2),
                                ingredientService.getIngredientById(3)),
                        sizeService.getSizeById(1),
                        27.40f

                )
        );
        Product product = productService.getAllProducts().get(2);
        System.out.println(product.getName());
        System.out.println(product.getIngredients());
    }
}
