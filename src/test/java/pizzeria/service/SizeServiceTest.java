package pizzeria.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzeria.model.Size;
import pizzeria.repository.SizeRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SizeServiceTest {

    @Mock
    SizeRepository sizeRepository;

    @InjectMocks
    SizeService sizeService;

    final List<Size> mockSizes = List.of(
            new Size(1,"Mała",32),
            new Size(2,"Duża",42)
    );



    @Test
    void should_return_all_ingredients() {
        Mockito.when(sizeRepository.getAllSizes()).thenReturn(mockSizes);

        //when
        List<Size> sizes = sizeService.getAllSizes();

        //then
        Assertions.assertThat(sizes.size()).isEqualTo(2);
        Assertions.assertThat(sizes.get(0).getName()).isEqualTo("Mała");
        Assertions.assertThat(sizes.get(1).getName()).isEqualTo("Duża");
        Assertions.assertThat(sizes.get(0).getDiameter()).isEqualTo(32);
        Assertions.assertThat(sizes.get(1).getDiameter()).isEqualTo(42);
    }

    @Test
    void should_return_specific_size_given_id() {
        Mockito.when(sizeRepository.getSizeById(1)).thenReturn(Optional.ofNullable(mockSizes.get(0)));

        //when
        Size result = sizeService.getSizeById(1).orElse(null);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("Mała");
        Assertions.assertThat(result.getDiameter()).isEqualTo(32);
    }

    @Test
    void should_return_null_when_size_not_found() {
        Mockito.when(sizeRepository.getSizeById(5)).thenReturn(Optional.empty());

        //when
        Size result = sizeService.getSizeById(5).orElse(null);

        //then
        Assertions.assertThat(result).isNull();
    }
}