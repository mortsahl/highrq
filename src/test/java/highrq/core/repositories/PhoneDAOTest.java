package highrq.core.repositories;

import highrq.core.models.entities.Phone;
import highrq.core.models.entities.enums.PhoneType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationConfig.xml")
public class PhoneDAOTest {
    @Autowired
    private PhoneDAO dao;

    private static final String AREACODE = "303";
    private static final String PREFIX = "888";
    private static final String BODY = "9876";
    private static final String EXT = "12345";

    private static final String AREACODE2 = "403";
    private static final String PREFIX2 = "888";
    private static final String BODY2 = "9876";
    private static final String EXT2 = "12345";

    private static final String TYPE = PhoneType.HOME.getValue();

    @Test
    @Transactional
    public void testFindPhone() {

        Phone phone = new Phone(AREACODE, PREFIX, BODY, EXT, TYPE);
        dao.createPhone(phone);

        Phone phone2 = dao.findPhone(phone.getId());
        assertNotNull(phone2);
        assertEquals(phone2.getAreaCode(), AREACODE);
        assertEquals(phone2.getPrefix(), PREFIX);
        assertEquals(phone2.getBody(), BODY);
        assertEquals(phone2.getExt(), EXT);
        assertEquals(phone2.getType(), TYPE);
    }

    @Test
    @Transactional
    public void testFindPhonesByAreaCode() {

        Phone phone1 = dao.createPhone(new Phone(AREACODE, PREFIX, BODY, EXT, TYPE));
        Phone phone2 = dao.createPhone(new Phone(AREACODE, PREFIX, BODY, EXT, TYPE));
        Phone phone3 = dao.createPhone(new Phone(AREACODE2, PREFIX2, BODY2, EXT2, TYPE));

        List<Phone> phones = dao.findPhonesByAreaCode(AREACODE);
        assertNotNull(phone1);
        assertNotNull(phone2);
        assertNotNull(phone3);

        assertEquals(phones.size(), 2);
        Phone ph = phones.get(0);
        assertEquals(ph.getAreaCode(), AREACODE);
    }

    @Test
    @Transactional
    public void testDeletePhone() {

        // Create a phone to delete
        Phone phone = dao.createPhone(new Phone(AREACODE, PREFIX, BODY, EXT, TYPE));
        Phone returnedPhone = dao.findPhone(phone.getId());
        assertNotNull((returnedPhone));

        // now delete it
        dao.deletePhone(returnedPhone.getId());

        // now make sure it is gone
        assertNull(dao.findPhone(phone.getId()));
    }

    @Test
    @Transactional
    public void testPhoneUpdate() {

        String AC = "999";

        // Create a phone to update
        Phone phone = dao.createPhone(new Phone(AREACODE, PREFIX, BODY, EXT, TYPE));
        assertEquals(phone.getAreaCode(), AREACODE);

        phone.setAreaCode(AC);;

        //update it
        phone = dao.updatePhone(phone);

        // now check that is was updated
        assertEquals(phone.getAreaCode(), AC);

    }
}
