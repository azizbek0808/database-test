package org.example.domain.beta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "beta2_entity")
@NoArgsConstructor
public class BetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String data;

    private Integer count;

    public BetaEntity(String data, Integer count) {
        this.data = data;
        this.count = count;
    }
}
