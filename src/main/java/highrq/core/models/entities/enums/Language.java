package highrq.core.models.entities.enums;

public enum Language {

    ENGLISH("English "),
    BENGALI("Bengali"),
    CANTONESE("Cantonese"),
    FRENCH("French"),
    FARSI("Farsi"),
    GERMAN("German"),
    HINDI("Hindi"),
    JAPANESE("Japanese"),
    JAVANESE("Javanese"),
    KOREAN("Korean"),
    MANDARIN("Mandarin"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    TAMIL("Tamil"),
    TELUGU("Telugu"),
    THAI("Thai"),
    TURKISH("Turkish"),
    URDU("Urdu"),
    VIETNAMESE("Vietnamese");

    private String value;

    private Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
