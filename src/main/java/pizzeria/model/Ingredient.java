package pizzeria.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pizzeria.entity.IngredientEntity;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Ingredient {
    private final int id;
    private final String name;
    private final boolean isVegetarian;
    private final String allergens;

    public static Ingredient fromEntity(IngredientEntity ingredientEntity) {
        return new Ingredient(
                ingredientEntity.getId(),
                ingredientEntity.getName(),
                ingredientEntity.isVegetarian(),
                ingredientEntity.getAllergens());
    }
}
