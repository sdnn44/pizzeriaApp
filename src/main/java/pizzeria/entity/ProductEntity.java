package pizzeria.entity;

import lombok.*;
import pizzeria.model.Ingredient;
import pizzeria.model.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductEntity {
    @PersistenceContext
    private static EntityManager entityManager;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "products_ingredients",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    private List<IngredientEntity> ingredients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @Column(name = "price")
    private Number price;

    public ProductEntity(String name, List<IngredientEntity> ingredients, SizeEntity size, Number price) {
        this.name = name;
        this.ingredients = ingredients;
        this.size = size;
        this.price = price;
    }


    public static ProductEntity fromProduct(Product product) {
        return new ProductEntity(
                product.getName(),
                entityManager.createQuery("SELECT productEntity FROM ProductEntity productEntity WHERE product_id IN :ids",IngredientEntity.class)
                    .setParameter("ids",product.getIngredients().stream().map(Ingredient::getId).toList()).getResultList(),
                entityManager.find(SizeEntity.class,product.getSize().getId()),
                product.getPrice()
        );
    }
}
