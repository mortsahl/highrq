package highrq.core.models.entities.enums;

public enum Field {

    ACCOUNTING("Accounting"),
    AGRICULTURE("Agriculture"),
    CIS("Computer Information Systems"),
    COMP_SCI("Computer Science"),
    BIOLOGY("Biology"),
    CHEMISTRY("Chemistry"),
    ENGINEERING("Engineering"),
    HISTORY("History"),
    LIBERAL_ARTS("Liberal Arts"),
    MATH("Mathematics"),
    PHILOSOPHY("Philosophy"),
    POLI_SCI("Political Science"),
    PSYCHOLOGY("Psychology"),
    ZOOLOGY("Zoology"),
    OTHER("Other");

    private final String value;

    private Field(String value) {
        this.value = value;
    }
}
