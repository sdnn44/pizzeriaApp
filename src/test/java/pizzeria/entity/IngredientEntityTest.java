package pizzeria.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzeria.model.Ingredient;

import static org.junit.jupiter.api.Assertions.*;

class IngredientEntityTest {

    @Test
    void should_return_entity_given_ingredient() {
        //given
        Ingredient ingredient = new Ingredient(1, "Sos", true, null);

        //when
        IngredientEntity ingredientEntity = IngredientEntity.fromIngredient(ingredient);

        //then
        Assertions.assertThat(ingredientEntity.getName()).isEqualTo(ingredient.getName());
        Assertions.assertThat(ingredientEntity.isVegetarian()).isEqualTo(ingredient.isVegetarian());
        Assertions.assertThat(ingredientEntity.getAllergens()).isEqualTo(ingredient.getAllergens());
    }
}