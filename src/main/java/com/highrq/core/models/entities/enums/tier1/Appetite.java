package com.highrq.core.models.entities.enums.tier1;

public enum Appetite {

    NONE("Non-Existent"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    ALWAYS("Can't get enough");

    private final String value;

    private Appetite(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
