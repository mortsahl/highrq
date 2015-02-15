package com.highrq.core.repositories;

import com.highrq.core.models.entities.Account;
import com.highrq.core.models.entities.enums.Role;
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
@ContextConfiguration("classpath:spring/applicationConfig.xml")
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

        Account foundAccounts = dao.findAccount(account.getId());
        assertNotNull(foundAccounts);
        assertEquals(foundAccounts.getUsername(), "name");
        assertEquals(foundAccounts.getPassword(), "password");
        assertEquals(foundAccounts.getRole(), Role.USER.toString());
    }

    @Test
    @Transactional
    public void testFindAccountsByRole() {

        // create 3 accounts
        dao.createAccount(new Account("George", "password", Role.USER.toString()));
        dao.createAccount(new Account("Paul", "password", Role.USER.toString()));
        dao.createAccount(new Account("John", "password", Role.ADMIN.toString()));

        List<Account> foundAccounts = dao.findAccountsByRole(Role.USER.toString());
        assertEquals((long) foundAccounts.size(), 2L);

        foundAccounts = dao.findAccountsByRole(Role.ADMIN.toString());
        assertEquals((long) foundAccounts.size(), 1L);

        foundAccounts = dao.findAccountsByRole(Role.GUEST.toString());
        assertEquals((long) foundAccounts.size(), (long) 0);  // Should this be an exception instead?
    }

    // TODO - sja: Need more tests
}
