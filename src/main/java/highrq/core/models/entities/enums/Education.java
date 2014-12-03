package highrq.core.models.entities.enums;

public enum Education {

    EIGHTH("Eighth"),
    HIGH("High School"),
    SOME("Some College"),
    AA("Associate of Arts"),
    BA("Bachelor’s"),
    PHD("Doctorate");


    private final String value;

    private Education(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
