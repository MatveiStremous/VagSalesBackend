package com.example.vagsalesbackend.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum Transmission {
    AUTOMATIC("Автомат"),
    MANUAL("Механика"),
    ROBOTIC("Робот"),
    VARIATOR("Вариатор");

    private final String prefix;

    Transmission(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static List<String> getAllPrefixes() {
        List<String> prefixes = new ArrayList<>();
        for (Transmission transmission : Transmission.values()) {
            prefixes.add(transmission.prefix);
        }
        return prefixes;
    }

    public static Transmission getByPrefix(String prefix){
        for(Transmission transmission: Transmission.values()){
            if(prefix.equals(transmission.getPrefix())){
                return transmission;
            }
        }
        return Transmission.MANUAL;
    }
}
