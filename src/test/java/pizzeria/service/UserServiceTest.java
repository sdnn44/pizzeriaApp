package pizzeria.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.Address;
import pizzeria.model.User;
import pizzeria.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    private final List<User> mockUsers = List.of(
            new User(
                    1, "Lukasz", "Kowalski", "lkowal", "lkowal@wp.pl", "kowal123", "kowal123", new Address(1, "Perlowa", "8", "33-333", "Lublin")
            ),
            new User(
                    2, "Michal", "Nowak", "mnowak", "lkowal@wp.pl", "kowal123", "kowal123", new Address(1, "Perlowa", "8", "33-333", "Lublin")
            ),
            new User(
                    3, "Edward", "Celinski", "ecel", "lkowal@wp.pl", "kowal123", "kowal123", new Address(1, "Perlowa", "8", "33-333", "Lublin")
            )
    );

    @Test
    void should_return_all_users() {
        Mockito.when(userRepository.getAllUsers()).thenReturn(mockUsers);

        //when
        List<User> result = userService.getAllUsers();

        //then
        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getFirstName()).isEqualTo("Lukasz");
        Assertions.assertThat(result.get(2).getFirstName()).isEqualTo("Edward");
        Assertions.assertThat(result.get(1).getId()).isEqualTo(2);

    }

    @Test
    void should_return_user_by_given_id() {
        Mockito.when(userRepository.getUserById(3)).thenReturn(Optional.ofNullable(mockUsers.get(2)));

        //when
        User result = userService.getUserById(3).orElse(null);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getFirstName()).isEqualTo("Edward");

    }
}