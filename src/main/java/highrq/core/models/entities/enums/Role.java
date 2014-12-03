package highrq.core.models.entities.enums;

public enum Role {

    GUEST("Guest"),
    USER("User"),
    ADMIN("Admin");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

