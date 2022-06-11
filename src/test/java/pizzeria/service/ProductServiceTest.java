package pizzeria.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.Ingredient;
import pizzeria.model.Product;
import pizzeria.model.Size;
import pizzeria.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    private final List<Product> mockProducts = List.of(
            new Product(1,
                    "Pizza1",
                    List.of(
                            new Ingredient(1, "Sos", true, null),
                            new Ingredient(2, "Ser", true, "laktoza")),
                    new Size(1, "Mała", 32),
                    21.10f
            ),
            new Product(2,
                    "Pizza2",
                    List.of(
                            new Ingredient(1, "Sos", true, null),
                            new Ingredient(2, "Ser", true, "laktoza"),
                            new Ingredient(3, "Szynka", false, null)
                    ),
                    new Size(2, "Duża", 42),
                    32.10f
            )
    );

    @Test
    void should_return_all_products() {
        Mockito.when(productRepository.getAllProducts()).thenReturn(mockProducts);

        //when
        List<Product> result = productService.getAllProducts();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getIngredients().size()).isEqualTo(3);
        Assertions.assertThat(result.get(1).getSize().getDiameter()).isEqualTo(42);
        Assertions.assertThat(result.get(0).getPrice()).isEqualTo(21.10f);

    }

    @Test
    void should_return_specific_product_given_id() {
        Mockito.when(productRepository.getProductById(1)).thenReturn(Optional.ofNullable(mockProducts.get(0)));

        //when
        Product result = productService.getProductById(1).orElse(null);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1);
        Assertions.assertThat(result.getIngredients().size()).isEqualTo(2);
        Assertions.assertThat(result.getSize().getDiameter()).isEqualTo(32);
        Assertions.assertThat(result.getPrice()).isEqualTo(21.10f);
    }

    @Test
    void should_return_null_when_product_not_found() {
        Mockito.when(productRepository.getProductById(5)).thenReturn(Optional.empty());

        //when
        Product result = productService.getProductById(5).orElse(null);

        //then
        Assertions.assertThat(result).isNull();
    }

    @Test
    void should_return_true_when_added_product() {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(true);

        //when
        boolean result = productService.addProduct(new Product(2,
                "Pizza2",
                List.of(
                        new Ingredient(1, "Sos", true, null),
                        new Ingredient(2, "Ser", true, "laktoza"),
                        new Ingredient(3, "Szynka", false, null)
                ),
                new Size(2, "Duża", 42),
                32.10f
        ));

        //then
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void should_return_products_of_given_size() {
        Mockito.when(productRepository.getAllProducts()).thenReturn(mockProducts);

        //when
        List<Product> result = productService.getProductsBySize(2);

        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getIngredients().size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getSize().getDiameter()).isEqualTo(42);
        Assertions.assertThat(result.get(0).getPrice()).isEqualTo(32.10f);

    }

    @Test
    void should_return_empty_list_when_given_wrong_size() {
        Mockito.when(productRepository.getAllProducts()).thenReturn(mockProducts);

        //when
        List<Product> result = productService.getProductsBySize(4);

        //then
        Assertions.assertThat(result.size()).isEqualTo(0);

    }


}