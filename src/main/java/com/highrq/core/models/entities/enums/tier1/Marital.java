package com.highrq.core.models.entities.enums.tier1;

public enum Marital {

    NEVER("Never married"),
    SEPARATED("Married but separated"),
    AFFAIR("Married but want affair"),
    LIVING("Living with partner, planning on separation");

    private final String value;

    private Marital(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
