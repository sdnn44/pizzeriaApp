package pizzeria.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pizzeria.entity.AddressEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Getter

public class Address {
    private final int id;
    private final String streetName;
    private final String houseNumber;
    private final String postalCode;
    private final String city;

    public static Address fromEntity(AddressEntity address) {
        return new Address(
                address.getId(),
                address.getStreetName(),
                address.getHouseName(),
                address.getPostalCode(),
                address.getCity()
        );
    }
    public static AddressEntity fromAddress(Address address) {
        return new AddressEntity(address.getId(), address.getStreetName(), address.getHouseNumber(), address.getPostalCode(), address.getCity());
    }
}
