package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.IngredientEntity;
import pizzeria.model.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<Ingredient> getIngredientById(int id){
        IngredientEntity ingredientEntity = entityManager.find(IngredientEntity.class, id);
        if(ingredientEntity == null) return Optional.empty();
        else return Optional.of(Ingredient.fromEntity(ingredientEntity));

    }

    public List<Ingredient> getMultipleIngredientsByIds(List<Integer> ids){
        return entityManager.createQuery("SELECT ingredientEntity FROM ProductEntity productEntity WHERE product_id IN :ids",IngredientEntity.class)
                .setParameter("ids",ids).getResultList().stream().map(Ingredient::fromEntity).toList();
    }

    @Transactional
    public boolean save(Ingredient ingredient){
        try {
            entityManager.persist(IngredientEntity.fromIngredient(ingredient));
            return true;
        }catch (PersistenceException e){
            return false;
        }
    }
}
