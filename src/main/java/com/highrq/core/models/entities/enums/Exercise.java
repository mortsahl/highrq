package com.highrq.core.models.entities.enums;

public enum Exercise {

    NEVER("Never"),
    OCCASIONAL("Occasional"),
    FANATIC("Fanatical");

    private final String value;

    private Exercise(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
