package pizzeria.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzeria.entity.IngredientEntity;
import pizzeria.entity.ProductEntity;
import pizzeria.entity.SizeEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void should_create_product_given_product_entity() {
        //given
        ProductEntity productEntity = new ProductEntity(
                "Pizza1",
                List.of(
                        new IngredientEntity("Sos",true,null),
                        new IngredientEntity("Ser",true,"laktoza")
                ),
                new SizeEntity("Mała",32),
                20.10f
        );

        //when
        Product product = Product.fromEntity(productEntity);

        //then
        Assertions.assertThat(product.getId()).isEqualTo(productEntity.getId());
        Assertions.assertThat(product.getName()).isEqualTo(productEntity.getName());
        Assertions.assertThat(product.getIngredients().get(0).getId()).isEqualTo(productEntity.getIngredients().get(0).getId());
        Assertions.assertThat(product.getIngredients().get(0).getName()).isEqualTo(productEntity.getIngredients().get(0).getName());
        Assertions.assertThat(product.getIngredients().get(0).isVegetarian()).isEqualTo(productEntity.getIngredients().get(0).isVegetarian());
        Assertions.assertThat(product.getIngredients().get(0).getAllergens()).isEqualTo(productEntity.getIngredients().get(0).getAllergens());
        Assertions.assertThat(product.getIngredients().get(1).getId()).isEqualTo(productEntity.getIngredients().get(1).getId());
        Assertions.assertThat(product.getIngredients().get(1).getName()).isEqualTo(productEntity.getIngredients().get(1).getName());
        Assertions.assertThat(product.getIngredients().get(1).isVegetarian()).isEqualTo(productEntity.getIngredients().get(1).isVegetarian());
        Assertions.assertThat(product.getIngredients().get(1).getAllergens()).isEqualTo(productEntity.getIngredients().get(1).getAllergens());
        Assertions.assertThat(product.getSize().getId()).isEqualTo(productEntity.getSize().getId());
        Assertions.assertThat(product.getSize().getName()).isEqualTo(productEntity.getSize().getName());
        Assertions.assertThat(product.getSize().getDiameter()).isEqualTo(productEntity.getSize().getDiameter());
        Assertions.assertThat(product.getPrice()).isEqualTo(productEntity.getPrice());
    }
}