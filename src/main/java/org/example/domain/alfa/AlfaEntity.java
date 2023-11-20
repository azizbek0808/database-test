package org.example.domain.alfa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "alfa")
public class AlfaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    private String data;

    private Integer count;

}
