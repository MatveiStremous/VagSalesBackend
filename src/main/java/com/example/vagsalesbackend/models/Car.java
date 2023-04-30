package com.example.vagsalesbackend.models;

import com.example.vagsalesbackend.models.enums.BodyType;
import com.example.vagsalesbackend.models.enums.FuelType;
import com.example.vagsalesbackend.models.enums.Transmission;
import lombok.*;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Double engineCapacity;

    private Year year;

    private String imageURL;

    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Model model;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "car")
    private List<Request> requests;
}
