package com.example.vagsalesbackend.models;

import com.example.vagsalesbackend.models.enums.RequestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String name;

    private String email;

    private String phone;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Car car;

    @PrePersist
    public void setDate() {
        this.date = LocalDate.now();
    }
}
