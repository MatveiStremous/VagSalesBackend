package com.example.vagsalesbackend.models.enums;

public enum RequestStatus {
    CANCELLED("Отменена"),
    DECORATED("Оформлена"),
    PROCESSING("Обрабатывается"),
    COMPLETED("Выполнена");

    private final String prefix;

    RequestStatus(String status) {
        this.prefix = status;
    }

    public String getPrefix() {
        return prefix;
    }

    public static RequestStatus getByPrefix(String prefix){
        for(RequestStatus requestStatus: RequestStatus.values()){
            if(prefix.equals(requestStatus.getPrefix())){
                return requestStatus;
            }
        }
        return RequestStatus.DECORATED;
    }
}
