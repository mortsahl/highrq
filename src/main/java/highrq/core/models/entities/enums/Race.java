package highrq.core.models.entities.enums;

public enum Race {
    WHITE("White"),
    BLACK("African American"),
    HISPANIC("Hispanic"),
    ASIAN("Asian"),
    AMERINDIAN("American Indian"),
    POLYNESIAN("Polynesian"),
    MIDEAST("Middle Eastern"),
    ARABIC("Arabic"),
    MIXED("Nixed"),
    OTHER("Other");

    private String value;

    private Race(String value) {
        this.value = value;
    }
}
