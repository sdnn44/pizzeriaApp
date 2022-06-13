package pizzeria.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="addresses", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class AddressEntity {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street_name")
    String streetName;

    @Column(name = "house_name")
    String houseName;

    @Column(name = "postal_code")
    String postalCode;

    @Column(name = "city")
    String city;

    public AddressEntity(String streetName, String houseName, String postalCode, String city) {
        this.streetName = streetName;
        this.houseName = houseName;
        this.postalCode = postalCode;
        this.city = city;
    }
}
