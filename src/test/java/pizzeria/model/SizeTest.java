package pizzeria.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pizzeria.entity.SizeEntity;

import static org.junit.jupiter.api.Assertions.*;

class SizeTest {

    @Test
    void should_create_size_given_size_entity() {
        //given
        SizeEntity sizeEntity = new SizeEntity("Małą",32);

        //when
        Size size = Size.fromEntity(sizeEntity);

        //then
        Assertions.assertThat(size.getId()).isEqualTo(sizeEntity.getId());
        Assertions.assertThat(size.getName()).isEqualTo(sizeEntity.getName());
        Assertions.assertThat(size.getDiameter()).isEqualTo(sizeEntity.getDiameter());

    }
}