package highrq.core.models.entities.enums;

public enum Culture {

    LIKE("I like plays, musicals, concerts"),
    NOT("I don't like plays, musicals, concerts");

    private final String value;

    private Culture(String value) {
        this.value = value;
    }
}
