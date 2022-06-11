package pizzeria.entity;

import lombok.*;
import pizzeria.model.Ingredient;

import javax.persistence.*;

@Entity
@Table(name="ingredients",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class IngredientEntity {
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_vegetarian")
    private boolean isVegetarian;

    @Column(name = "allergens")
    private String allergens;

    public IngredientEntity(String name, boolean isVegetarian, String allergens) {
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.allergens = allergens;
    }

    public static IngredientEntity fromIngredient(Ingredient ingredient){
        return new IngredientEntity(ingredient.getName(),ingredient.isVegetarian(),ingredient.getAllergens());
    }
}
