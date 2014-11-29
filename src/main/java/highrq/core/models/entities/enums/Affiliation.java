package highrq.core.models.entities.enums;

public enum Affiliation {

    NONE("Independent"),
    GOP("Republican Party"),
    DEM("Democrat Party"),
    LIBERTARIAN("Libertarian Party"),
    GREEN("Green Party"),
    CONSTITUTION("Constitution Party"),
    COMMUNIST("Communist Party USA"),
    SOCIALIST("Socialist Party USA");

    private final String value;

    private Affiliation(String value) {
        this.value = value;
    }
}
