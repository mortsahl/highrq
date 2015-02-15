package com.highrq.aspect;

import com.highrq.core.services.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationConfig.xml")
public class LoggingAspectTest {

    @Autowired
    private LoggingAspect aspect;

    @Autowired
    private AccountService accountService;


    // This tests only the before
    @Test
    public void aspectCalled() {
        assertFalse(aspect.isEnteringCalled());
        accountService.findAllAccounts();
        assertTrue(aspect.isEnteringCalled());
    }

}
