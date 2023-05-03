package com.example.vagsalesbackend.models.enums;

public enum Role {
    ROLE_USER("Пользователь"),
    ROLE_MANAGER("Менеджер"),
    ROLE_ADMIN("Администратор");

    private final String prefix;

    Role(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static Role getByPrefix(String prefix){
        for(Role role: Role.values()){
            if(prefix.equals(role.getPrefix())){
                return role;
            }
        }
        return Role.ROLE_USER;
    }
}
