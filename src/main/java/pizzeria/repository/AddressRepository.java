package pizzeria.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pizzeria.entity.AddressEntity;
import pizzeria.model.Address;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Address> getAllAddresses() {
        return entityManager.createQuery("SELECT addressEntity FROM AddressEntity addressEntity", AddressEntity.class)
                .getResultList()
                .stream()
                .map(Address::fromEntity)
                .toList();
    }

    public Optional<Address> getAddressById(int id) {
        AddressEntity addressEntity = entityManager.find(AddressEntity.class, id);
        if (addressEntity == null) return Optional.empty();
        else return Optional.of(Address.fromEntity(addressEntity));
    }


}
