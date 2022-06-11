package pizzeria.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pizzeria.entity.ProductEntity;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class Product {
    private final int id;
    private final String name;
    private final List<Ingredient> ingredients;
    private final Size size;
    private final Number price;

    public static Product fromEntity(ProductEntity product) {
        return new Product(
                product.getId(),
                product.getName(),
                product.getIngredients().stream().map(Ingredient::fromEntity).toList(),
                Size.fromEntity(product.getSize()),
                product.getPrice()
        );
    }
}
