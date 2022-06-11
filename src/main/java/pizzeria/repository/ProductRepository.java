package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.IngredientEntity;
import pizzeria.entity.ProductEntity;
import pizzeria.entity.SizeEntity;
import pizzeria.model.Ingredient;
import pizzeria.model.Product;
import pizzeria.model.Size;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final IngredientRepository ingredientRepository;
    private final SizeRepository sizeRepository;

    public List<Product> getAllProducts(){
        return entityManager.createQuery("SELECT productEntity FROM ProductEntity productEntity", ProductEntity.class)
                .getResultList()
                .stream()
                .map(Product::fromEntity)
                .toList();
    }

    public Product getProductById(int productId){
        return Product.fromEntity(entityManager.find(ProductEntity.class,productId));
    }

    @Transactional
    public void save(Product product){
        List<IngredientEntity> ingredients = entityManager.createQuery("SELECT ingredientEntity FROM IngredientEntity ingredientEntity WHERE ingredient_id IN :ids",IngredientEntity.class)
                .setParameter("ids",product.getIngredients().stream().map(Ingredient::getId).toList()).getResultList();
        SizeEntity size = entityManager.find(SizeEntity.class,product.getSize().getId());
        entityManager.persist(new ProductEntity(
                product.getName(),
                ingredients,
                size,
                product.getPrice()
        ));
    }
}
