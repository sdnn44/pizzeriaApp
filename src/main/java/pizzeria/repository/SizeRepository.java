package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.SizeEntity;
import pizzeria.model.Size;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
    public Optional<Size> getSizeById(int id){
        SizeEntity sizeEntity = entityManager.find(SizeEntity.class, id);
        if (sizeEntity==null) return Optional.empty();
        return Optional.of(Size.fromEntity(sizeEntity));

    }
}
