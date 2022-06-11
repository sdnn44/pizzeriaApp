package pizzeria.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="sizes",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SizeEntity {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    String name;

    @Column(name = "diameter")
    Integer diameter;

    public SizeEntity(String name, Integer diameter) {
        this.name = name;
        this.diameter = diameter;
    }
}
