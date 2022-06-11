package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzeria.model.Ingredient;
import pizzeria.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.getAllIngredients();
    }

    public boolean addIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> getIngredientById(int id){
        return ingredientRepository.getIngredientById(id);
    }
}
