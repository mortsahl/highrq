package highrq.core.models.entities.enums;

public enum Politics {

    ACTIVE("Politically Active"),
    INTERESTED("Politically interested"),
    KINDA("Not very interested in politics"),
    DISINTERESTED("No interest in politics");

    private final String value;

    private Politics(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
