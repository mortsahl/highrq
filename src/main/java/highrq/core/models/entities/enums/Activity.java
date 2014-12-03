package highrq.core.models.entities.enums;

public enum Activity {

    INDOOR("I prefer indoor activities"),
    OUTDOOR("I prefer outdoor activities"),
    BOTH("I like both");

    private final String value;

    private Activity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
