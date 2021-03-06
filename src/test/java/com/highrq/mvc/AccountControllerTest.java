package com.highrq.mvc;

import com.highrq.api.controllers.impl.AccountControllerImpl;
import com.highrq.core.models.entities.Account;
import com.highrq.core.models.entities.Blog;
import com.highrq.core.models.entities.enums.Role;
import com.highrq.core.services.AccountService;
import com.highrq.core.services.exceptions.AccountDoesNotExistException;
import com.highrq.core.services.exceptions.AccountExistsException;
import com.highrq.core.services.exceptions.BlogExistsException;
import com.highrq.core.services.util.AccountList;
import com.highrq.core.services.util.BlogList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {
    @InjectMocks
    private AccountControllerImpl controller;

    @Mock
    private AccountService service;

    private MockMvc mockMvc;
    private ArgumentCaptor<Account> accountCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        accountCaptor = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void findAllBlogsForAccount() throws Exception {
        List<Blog> list = new ArrayList<>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(service.findBlogsByAccount(1L)).thenReturn(blogList);

        mockMvc.perform(get("/api/accounts/1/blogs"))
                .andExpect(jsonPath("$.blogs[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllBlogsForNonExistingAccount() throws Exception {
        List<Blog> list = new ArrayList<>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

      //  BlogList blogList = new BlogList(list);

        when(service.findBlogsByAccount(1L)).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/api/accounts/1/blogs"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBlogExistingAccount() throws Exception {
        Blog createdBlog = new Blog();
        createdBlog.setId(1L);
        createdBlog.setTitle("Test Title");

        when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(createdBlog);

        mockMvc.perform(post("/api/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1"))))
                .andExpect(header().string("Location", endsWith("/blogs/1")))
                .andExpect(status().isCreated());
    }

    @Test
    public void createBlogNonExistingAccount() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(post("/api/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBlogExistingBlogName() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

        mockMvc.perform(post("/api/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setUsername("test");

        when(service.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/api/accounts")
                .content("{\"username\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", endsWith("/api/accounts/1")))
                .andExpect(jsonPath("$.username", is(createdAccount.getUsername())))
                .andExpect(status().isCreated());

        verify(service).createAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getPassword();
        assertEquals("test", password);
    }

    @Test
    public void createAccountExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setUsername("test");

        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post("/api/accounts")
                .content("{\"username\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setPassword("test");
        foundAccount.setUsername("test");

        when(service.findAccount(1L)).thenReturn(foundAccount);

        mockMvc.perform(get("/api/accounts/1"))
//                .andDo(print())
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(jsonPath("$.username", is(foundAccount.getUsername())))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(endsWith("self"), endsWith("blogs"))))
                        .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingAccount() throws Exception {
        when(service.findAccount(1L)).thenReturn(null);

        mockMvc.perform(get("/api/accounts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setUsername("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setUsername("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/api/accounts"))
                .andExpect(jsonPath("$.accounts[*].username", hasItems(endsWith("accountA"), endsWith("accountB"))))
                .andExpect(status().isOk());
    }

    @Test
    public void findAccountByUsername() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setUsername("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setUsername("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/api/accounts").param("username", "accountA"))
                .andExpect(jsonPath("$.accounts[*].username", everyItem(endsWith("accountA"))))
                .andExpect(status().isOk());
    }

    @Test
    public void findAccountsByRole() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setUsername("accountA");
        accountA.setRole(Role.GUEST.getValue());
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setUsername("accountB");
        accountB.setRole(Role.GUEST.getValue());
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);
        when(service.findAccountsByRole(Role.GUEST.getValue())).thenReturn(accountList);

        mockMvc.perform(get("/api/accounts/role/Guest"))
                .andExpect(jsonPath("$.accounts[*].username", hasItems(endsWith("accountA"), endsWith("accountB"))))
                .andExpect(status().isOk());
    }

}
