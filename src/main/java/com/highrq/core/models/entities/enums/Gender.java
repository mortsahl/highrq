package com.highrq.core.models.entities.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    TRANSMF("Transgender - Male to Female"),
    TRANSFM("Transgender - Female to Male");

    private String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

