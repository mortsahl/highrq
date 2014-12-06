package highrq.core.models.entities.enums.tier1;

public enum Race {
    WHITE("White"),
    BLACK("African American"),
    HISPANIC("Hispanic"),
    ASIAN("Asian"),
    AMERINDIAN("American Indian"),
    POLYNESIAN("Polynesian"),
    MIDEAST("Middle Eastern"),
    ARABIC("Arabic"),
    MIXED("Mixed"),
    OTHER("Other");

    private String value;

    private Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
