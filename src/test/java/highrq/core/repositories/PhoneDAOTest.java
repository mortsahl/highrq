package highrq.core.repositories;

import highrq.core.models.entities.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-config.xml")
public class PhoneDAOTest {
    @Autowired
    private PhoneDAO dao;

    private Phone phone;

    private static final String AREACODE = "303";
    private static final String PREFIX = "888";
    private static final String BODY = "9876";
    private static final String EXT = "12345";

    private static final String AREACODE2 = "403";
    private static final String PREFIX2 = "888";
    private static final String BODY2 = "9876";
    private static final String EXT2 = "12345";


    @Before
    @Transactional
    @Rollback(false)
    public void setup()
    {
        phone = new Phone(AREACODE, PREFIX, BODY, EXT);
        dao.createPhone(phone);
    }

    @Test
    @Transactional
    public void testFind() {
        Phone phone = dao.findPhone(this.phone.getId());
        assertNotNull(phone);
        assertEquals(phone.getAreaCode(), AREACODE);
        assertEquals(phone.getPrefix(), PREFIX);
        assertEquals(phone.getBody(), BODY);
        assertEquals(phone.getExt(),EXT);
    }

    @Test
    @Transactional
    public void testFindPhonesByAreaCode() {

        Phone phone1 =  dao.createPhone(new Phone(AREACODE, PREFIX, BODY, EXT));
        Phone phone2 =  dao.createPhone(new Phone(AREACODE, PREFIX+1, BODY, EXT));
        Phone phone3 =  dao.createPhone(new Phone(AREACODE2, PREFIX2, BODY2, EXT2));

        System.out.println(phone1.getId());
        System.out.println(phone2.getId());
        System.out.println(phone3.getId());

        List<Phone> phones = dao.findPhonesByAreaCode(AREACODE);
        assertNotNull(phone);
        assertEquals(phones.size(), 2);
        Phone ph = phones.get(0);
        assertEquals(ph.getAreaCode(), AREACODE);

    }
}
