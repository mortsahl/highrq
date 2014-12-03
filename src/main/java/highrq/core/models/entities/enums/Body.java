package highrq.core.models.entities.enums;

public enum Body {

    ECTO("Thin"),
    MESO("Average"),
    ENDO("Full Bodied");

    private final String value;

    private Body(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
