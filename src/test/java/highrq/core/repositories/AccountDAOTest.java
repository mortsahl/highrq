package highrq.core.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import highrq.core.models.entities.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountDAOTest {

    @Autowired
    private AccountDAO dao;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setup()
    {
        account = new Account();
        account.setName("name");
        account.setPassword("password");
        dao.createAccount(account);
    }

    @Test
    @Transactional
    public void testFind()
    {
        Account account = dao.findAccount(this.account.getId());
        assertNotNull(account);
        assertEquals(account.getName(), "name");
        assertEquals(account.getPassword(), "password");
    }
}
