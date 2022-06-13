package pizzeria.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzeria.entity.IngredientEntity;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void should_create_ingredient_given_ingredient_entity() {
        //given
        IngredientEntity ingredientEntity = new IngredientEntity("Sos",true,null);

        //when
        Ingredient ingredient = Ingredient.fromEntity(ingredientEntity);

        //then
        Assertions.assertThat(ingredient.getName()).isEqualTo(ingredientEntity.getName());
        Assertions.assertThat(ingredient.isVegetarian()).isEqualTo(ingredientEntity.isVegetarian());
        Assertions.assertThat(ingredient.getAllergens()).isEqualTo(ingredientEntity.getAllergens());

    }
}