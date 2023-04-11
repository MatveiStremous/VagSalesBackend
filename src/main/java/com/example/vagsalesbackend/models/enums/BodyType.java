package com.example.vagsalesbackend.models.enums;

import java.util.ArrayList;
import java.util.List;

public enum BodyType {
    SEDAN("Седан"),
    HATCHBACK("Хэтчбек"),
    OFF_ROAD("Внедорожник"),
    CABRIOLET("Кабриолет"),
    COUPE("Купе"),
    LIFTBACK("Лифтбек");

    private final String prefix;

    BodyType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static List<String> getAllPrefixes(){
        List<String> prefixes = new ArrayList<>();
        for(BodyType bodyType: BodyType.values()){
            prefixes.add(bodyType.prefix);
        }
        return prefixes;
    }

    public static BodyType getByPrefix(String prefix){
        for(BodyType bodyType: BodyType.values()){
            if(prefix.equals(bodyType.getPrefix())){
                return bodyType;
            }
        }
        return BodyType.SEDAN;
    }
}
