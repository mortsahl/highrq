package highrq.core.models.entities.enums.tier1;

public enum Preference {
    STRAIGHT("Straight"),
    GAY("Gay"),
    BI("Bi-Sexual");

    private String value;

    private Preference(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
};


