package com.highrq.core.models.entities.enums.tier1;

public enum Employment {

    NOT("Not employed"),
    PART("Part time employment"),
    FULL("Full time employment");

    private final String value;

    private Employment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
