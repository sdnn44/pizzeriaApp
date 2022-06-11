
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
                        List.of(ingredientService.getIngredientById(1).orElse(null),
                                ingredientService.getIngredientById(2).orElse(null),
                                ingredientService.getIngredientById(3).orElse(null)),
                        sizeService.getSizeById(1).orElse(null),
                        27.40f

                )
        );
        Product product = productService.getAllProducts().get(2);
        System.out.println(product.getName());
        System.out.println(product.getIngredients());
        Product product1 = productService.getProductById(5).orElse(null);
        System.out.println(product1==null?"nie ma":product1);

    }
}
