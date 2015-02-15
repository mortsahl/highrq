package com.highrq.core.models.entities.enums;

public enum Eyes {

    BLUE("Blue"),
    GREEN("Green"),
    HAZEL("Hazel"),
    GREY("Grey"),
    BROWN("Brown");

    private final String value;

    private Eyes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
