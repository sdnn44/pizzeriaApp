package pizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pizzeria.model.Ingredient;
import pizzeria.service.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class IngredientRestController {

    private final IngredientService ingredientService;

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }
}
