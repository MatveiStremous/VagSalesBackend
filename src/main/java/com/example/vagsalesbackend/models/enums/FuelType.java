package com.example.vagsalesbackend.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum FuelType {
    GASOLINE("Бензин"),
    DIESEL("Дизель"),
    ELECTRO("Электро");

    private final String prefix;

    FuelType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static List<String> getAllPrefixes(){
        List<String> prefixes = new ArrayList<>();
        for(FuelType fuelType: FuelType.values()){
            prefixes.add(fuelType.prefix);
        }
        return prefixes;
    }

    public static FuelType getByPrefix(String prefix){
        for(FuelType fuelType: FuelType.values()){
            if(prefix.equals(fuelType.getPrefix())){
                return fuelType;
            }
        }
        return FuelType.GASOLINE;
    }
}
