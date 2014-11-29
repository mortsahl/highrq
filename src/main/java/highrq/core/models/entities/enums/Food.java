package highrq.core.models.entities.enums;

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
}
