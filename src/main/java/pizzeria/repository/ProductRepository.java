package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.ProductEntity;
import pizzeria.model.Product;

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
        entityManager.persist(ProductEntity.fromProduct(product));
    }
}
