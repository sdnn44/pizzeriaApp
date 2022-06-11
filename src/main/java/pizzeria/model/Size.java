package pizzeria.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pizzeria.entity.SizeEntity;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Size {
    private final int id;
    private final String name;
    private final Integer diameter;

    public static Size fromEntity(SizeEntity sizeEntity) {
        return new Size(
                sizeEntity.getId(),
                sizeEntity.getName(),
                sizeEntity.getDiameter());
    }
}
