package highrq.core.models.entities.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String value;

    private Gender(String value) {
        this.value = value;
    }
};

