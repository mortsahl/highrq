package highrq.core.models.entities.enums;


public enum Appeal {

    // TODO - sja: Not too sure about these

    AVERAGE("Average"),
    VOLUPTUOUS("Voluptuous"),
    CURVY("Curvy"),
    CHISELED("Chiseled"),
    KNOCKOUT("Knockout");

    private final String value;

    private Appeal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
