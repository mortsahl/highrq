package com.highrq.core.models.entities.enums.tier1;

public enum Food {

    CARNIVORE("Meat eater"),
    OMNIVORE("Eat anything"),
    STRICT("Strict vegetarian"),
    PARTIAL("Mostly vegatarian"),
    VEGAN("Vegan");

    private String value;

    private Food(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
