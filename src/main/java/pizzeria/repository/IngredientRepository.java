package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.IngredientEntity;
import pizzeria.model.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IngredientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Ingredient> getAllIngredients(){
        return entityManager
                .createQuery("SELECT ingredientEntity FROM IngredientEntity ingredientEntity",IngredientEntity.class)
                .getResultList()
                .stream()
                .map(Ingredient::fromEntity)
                .toList();
    }

    @Transactional
    public void save(Ingredient ingredient){
        entityManager.persist(IngredientEntity.fromIngredient(ingredient));
    }
}
