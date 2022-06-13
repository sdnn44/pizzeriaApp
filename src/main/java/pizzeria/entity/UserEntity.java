package pizzeria.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode


public class UserEntity {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "login")
    String login;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "password_matching")
    String passwordMatching;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="address_id")
    private AddressEntity address;

    public UserEntity(String firstName, String lastName, String login, String email, String password, String passwordMatching, AddressEntity address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.passwordMatching = passwordMatching;
        this.address = address;
    }
}
