package com.highrq.core.models.entities.enums;

public enum Finance {

    NOT("Not financially secure"),
    SOSO("So So financially secure"),
    SECURE("Financially secure"),
    WELL("Doing well financially");

    private final String value;

    private Finance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
