package pizzeria.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.UserEntity;
import pizzeria.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT userEntity FROM UserEntity userEntity", UserEntity.class)
                .getResultList()
                .stream()
                .map(User::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(int id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if (userEntity == null) return Optional.empty();
        return Optional.of(User.fromEntity(userEntity));
    }
}
