package pizzeria.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Address {


    private  String city;
    private  String street;
    private  int building;
    private  int local;


    @Override
    public String toString() {
        return city +
                ", " + street +
                ", " + building +
                ", " + local;
    }
}
