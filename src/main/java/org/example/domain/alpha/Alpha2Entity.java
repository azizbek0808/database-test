package org.example.domain.alpha;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "alpha2_entity")
@NoArgsConstructor
public class Alpha2Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    private String data;

    private Integer count;

    public Alpha2Entity(String data, Integer count) {
        this.data = data;
        this.count = count;
    }
}
