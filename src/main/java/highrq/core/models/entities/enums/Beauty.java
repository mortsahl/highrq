package highrq.core.models.entities.enums;

public enum Beauty {

    LESS("Less then average attractiveness"),
    AVERAGE("Average attractiveness"),
    GOOD("Good looking"),
    DEAD("Drop dead gorgeous");

    private final String value;

    private Beauty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
