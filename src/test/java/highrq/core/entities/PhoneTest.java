package highrq.core.entities;

import highrq.core.models.entities.Phone;
import highrq.core.models.entities.enums.PhoneType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhoneTest {

    private static final String AREACODE = "303";
    private static final String PREFIX = "888";
    private static final String BODY = "9876";
    private static final String EXT = "12345";
    private static final String TYPE = PhoneType.HOME.getValue();

    private StringBuilder sb = new StringBuilder();

    @Before
    public void Setup() {
        sb.setLength(0);
    }

    @Test
    public void testGetPhoneNumberWithExtension() {
        Phone phone = new Phone(AREACODE, PREFIX, BODY, EXT, TYPE);
        assertEquals((sb.append("(").append(AREACODE).append(") ").append(PREFIX).append("-").append(BODY).append(" x").append(EXT)).toString(), phone.getFormattedPhoneNumber());
    }

    @Test
    public void testGetPhoneNumberWithoutExtension() {
        Phone phone = new Phone(AREACODE, PREFIX, BODY, TYPE);
        assertEquals((sb.append("(").append(AREACODE).append(") ").append(PREFIX).append("-").append(BODY)).toString(), phone.getFormattedPhoneNumber());
    }
}
