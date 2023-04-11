package com.example.vagsalesbackend.dto;

import com.example.vagsalesbackend.models.enums.BodyType;
import com.example.vagsalesbackend.models.enums.FuelType;
import com.example.vagsalesbackend.models.enums.Transmission;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarEnumsResponse {
    private List<String> bodyTypes = BodyType.getAllPrefixes();
    private List<String> fuelTypes = FuelType.getAllPrefixes();
    private List<String> transmissions = Transmission.getAllPrefixes();
}
