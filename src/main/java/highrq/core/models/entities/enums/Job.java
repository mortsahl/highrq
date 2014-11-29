package highrq.core.models.entities.enums;

public enum Job {

    ADMIN("Admin/Clerical"),
    ARTS("Arts"),
    ARCHITECTURE("Architecture"),
    BUSINESS("Business"),
    COMPUTER("Information Technology"),
    ECONOMIC("Economic"),
    EDUCATION("Education"),
    ENERGY("Energy"),
    ENGINEERING("Engineering"),
    ENTERTAINMENT("Entertainment"),
    ENTREPRENEUR("Entrepreneur"),
    ENVIRONMENTAL("Environmental"),
    EXECUTIVE("Executive"),
    FINANCIAL("Financial Services"),
    HEALTH("Health Care"),
    HOSPITALITY("Hospitality"),
    FITNESS("Fitness"),
    GEOGRAPHY("Geography"),
    GOVERNMENT("Government"),
    HR("Human Resources"),
    JOURNALISM("Journalism"),
    LAW("Law"),
    LIBRARY("Library Services"),
    LITERATURE("Literature"),
    MANUFACTURING("Manufacturing"),
    MEDICAL("Medical"),
    MILITARY("Military"),
    NONPROFIT("Non-Profit"),
    POLITICS("Politics"),
    RELIGION("Religion"),
    RETAIL("Retail"),
    SCIENCE("Science"),
    SEX("Sex Industry"),
    SOCIAL("Social Services"),
    STUDENT("Student"),
    TRANSPORTATION("Transportation"),
    OTHER("Other");

    private final String value;

    private Job(String value) {
        this.value = value;
    }
}
