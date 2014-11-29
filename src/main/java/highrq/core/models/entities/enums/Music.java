package highrq.core.models.entities.enums;

public enum Music {

    FIFTIES("Fifties"),
    SIXTIES("Sixties"),
    BAND("Big band"),
    CLASSIC("Classic rock"),
    METAL("Metal"),
    DISCO("Disco"),
    POP("Pop"),
    RB("R&B"),
    HIPHOP("Hip Hop"),
    RAP("Rap"),
    COUNTRY("Country"),
    OPERA("Opera"),
    SHOW("Show tunes");

    private final String value;

    private Music(String value) {
        this.value = value;
    }
}
