package pizzeria.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.Ingredient;
import pizzeria.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    IngredientRepository ingredientRepository;

    @InjectMocks
    IngredientService ingredientService;

    final List<Ingredient> mockIngredients = List.of(
            new Ingredient(1,"Sos",true,null),
            new Ingredient(2,"Ser",true,"laktoza")
    );



    @Test
    void should_return_all_ingredients() {
        Mockito.when(ingredientRepository.getAllIngredients()).thenReturn(mockIngredients);

        //when
        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        //then
        Assertions.assertThat(ingredients.size()).isEqualTo(2);
        Assertions.assertThat(ingredients.get(0).getName()).isEqualTo("Sos");
        Assertions.assertThat(ingredients.get(1).getAllergens()).isEqualTo("laktoza");
    }

    @Test
    void should_return_specific_ingredient_given_id() {
        Mockito.when(ingredientRepository.getIngredientById(1)).thenReturn(Optional.ofNullable(mockIngredients.get(0)));

        //when
        Ingredient ingredient = ingredientService.getIngredientById(1).orElse(null);

        //then
        Assertions.assertThat(ingredient).isNotNull();
        Assertions.assertThat(ingredient.getId()).isEqualTo(1);
        Assertions.assertThat(ingredient.getName()).isEqualTo("Sos");
        Assertions.assertThat(ingredient.isVegetarian()).isEqualTo(true);
        Assertions.assertThat(ingredient.getAllergens()).isNull();
    }

    @Test
    void should_return_null_when_ingredient_not_found() {
        Mockito.when(ingredientRepository.getIngredientById(4)).thenReturn(Optional.empty());

        //when
        Ingredient ingredient = ingredientService.getIngredientById(4).orElse(null);

        //then
        Assertions.assertThat(ingredient).isNull();
    }

    @Test
    void should_return_true_when_add_ingredient_success(){
        Mockito.when(ingredientRepository.save(Mockito.any(Ingredient.class))).thenReturn(true);

        //when
        Ingredient ingredient = new Ingredient(3,"Szynka",false,null);
        boolean result = ingredientService.addIngredient(ingredient);


        //then
        Assertions.assertThat(result).isEqualTo(true);
    }
}