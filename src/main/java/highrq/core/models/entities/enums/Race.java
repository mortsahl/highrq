package highrq.core.models.entities.enums;

public enum Race {
    WHITE("White"),
    BLACK("Black"),
    HISPANIC("HISPANIC"),
    ASIAN("Asian"),
    AMERINDIAN("AMERINDIAN"),
    POLYNEASIAN("POLYNEASIAN"),
    OTHER("Other");

    private String value;

    private Race(String value) {
        this.value = value;
    }
}
