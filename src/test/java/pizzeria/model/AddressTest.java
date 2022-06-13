package pizzeria.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void should_return_proper_string(){
        //given
        Address address = new Address("Warszawa","Wodna",1,1);

        //when
        String string = address.toString();

        //then
        Assertions.assertThat(string).isEqualTo("Warszawa, Wodna, 1, 1");
    }
}