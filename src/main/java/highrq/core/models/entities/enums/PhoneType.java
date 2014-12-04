package highrq.core.models.entities.enums;

public enum PhoneType {

    HOME("Home"),
    OFFICE("Office"),
    CELL("Cell"),
    OTHER("Other");

    private String value;

    private PhoneType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
