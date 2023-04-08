package com.example.vagsalesbackend.models;

import lombok.*;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private Integer engineCapacity;

    private Integer price;

    private Year year;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Model model;
}
