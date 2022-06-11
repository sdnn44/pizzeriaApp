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
}
