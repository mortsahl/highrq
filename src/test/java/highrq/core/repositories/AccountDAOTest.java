package highrq.core.repositories;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-config.xml")
public class AccountDAOTest {

    @Autowired
    private AccountDAO dao;
    private Account account;

    @Test
    @Transactional
    public void testFindAccount()
    {
        // create an account
        Account account = new Account("name", "password", Role.USER.toString());
        dao.createAccount(account);

        Account accnt = dao.findAccount(account.getId());
        assertNotNull(accnt);
        assertEquals(accnt.getName(), "name");
        assertEquals(accnt.getPassword(), "password");
        assertEquals(accnt.getRole(), Role.USER.toString());
    }

    @Test
    @Transactional
    public void testFindAccountsByRole() {

        // create 3 accounts
        dao.createAccount(new Account("George", "password", Role.USER.toString()));
        dao.createAccount(new Account("Paul", "password", Role.USER.toString()));
        dao.createAccount(new Account("John", "password", Role.ADMIN.toString()));

        List<Account> accnt = dao.findAccountsByRole(Role.USER.toString());
        assertEquals(accnt.size(), 2);

        accnt = dao.findAccountsByRole(Role.ADMIN.toString());
        assertEquals(accnt.size(), 1);

        accnt = dao.findAccountsByRole(Role.GUEST.toString());
        assertEquals(accnt.size(), 0);  // Should this be an exception instead?
    }

    // TODO - sja: Need more tests
}
