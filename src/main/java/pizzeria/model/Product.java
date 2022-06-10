package pizzeria.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
public class Product {
    private final String name;
    private final List<Ingredient> ingredients;
    private final Size size;
    private final Number price;
}
