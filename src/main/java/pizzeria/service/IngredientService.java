package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzeria.model.Ingredient;
import pizzeria.repository.IngredientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.getAllIngredients();
    }

    public void addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public Ingredient getIngredientById(int id){
        return ingredientRepository.getIngredientById(id);
    }
}
