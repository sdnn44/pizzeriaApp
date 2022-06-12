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
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_ingredients",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    private List<IngredientEntity> ingredients;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @Column(name = "price")
    private float price;

    public ProductEntity(String name, List<IngredientEntity> ingredients, SizeEntity size, float price) {
        this.name = name;
        this.ingredients = ingredients;
        this.size = size;
        this.price = price;
    }

}
