package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.SizeEntity;
import pizzeria.model.Size;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SizeRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Size> getAllSizes(){
        return entityManager.createQuery("SELECT sizeEntity FROM SizeEntity sizeEntity", SizeEntity.class)
                .getResultList()
                .stream()
                .map(Size::fromEntity)
                .toList();
    }
    public Size getSizeById(int id){
        return Size.fromEntity(entityManager.find(SizeEntity.class,id));
    }
}
