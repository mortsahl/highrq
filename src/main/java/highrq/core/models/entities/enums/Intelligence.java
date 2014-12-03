package highrq.core.models.entities.enums;


public enum Intelligence {

    DEFICIENT("IQ below 70 "),
    BORDERLINE("Between 70 and 79"),
    LOW("Between 80 and 89"),
    AVERAGE("Between 90 and 109"),
    HIGH("Between 110 and 119"),
    SUPERIOR("Between 120 and 129"),
    VERY("Greater than 130");

    private String value;

    private Intelligence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
