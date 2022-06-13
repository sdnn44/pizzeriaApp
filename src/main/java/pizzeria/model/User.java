package pizzeria.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pizzeria.entity.UserEntity;

import java.util.List;

@RequiredArgsConstructor
@Getter

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String login;
    private final String email;
    private final String password;
    private final String passwordMatching;
    private final Address address;

    public static User fromEntity(UserEntity user) {
        return new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmail(),
                user.getPassword(),
                user.getPasswordMatching(),
                Address.fromEntity(user.getAddress())
        );
    }
    public static UserEntity fromUser(User user) {
        return new UserEntity(user.getFirstName(), user.getLastName(), user.getLogin(), user.getEmail(), user.getPassword(), user.getPasswordMatching(), Address.fromAddress(user.getAddress()));
    }
}
