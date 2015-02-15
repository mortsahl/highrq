package com.highrq.api.controllers;

import com.highrq.api.exceptions.ConflictException;
import com.highrq.api.exceptions.NotFoundException;
import com.highrq.api.resources.AccountListResource;
import com.highrq.api.resources.AccountResource;
import com.highrq.api.resources.BlogListResource;
import com.highrq.api.resources.BlogResource;
import com.highrq.api.resources.assemblers.AccountListResourceAssembler;
import com.highrq.api.resources.assemblers.AccountResourceAssembler;
import com.highrq.api.resources.assemblers.BlogListResourceAssembler;
import com.highrq.api.resources.assemblers.BlogResourceAssembler;
import com.highrq.core.models.entities.Account;
import com.highrq.core.models.entities.Blog;
import com.highrq.core.services.AccountService;
import com.highrq.core.services.exceptions.AccountDoesNotExistException;
import com.highrq.core.services.exceptions.AccountExistsException;
import com.highrq.core.services.exceptions.BlogExistsException;
import com.highrq.core.services.util.AccountList;
import com.highrq.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "username", required = false) String username) {

        AccountList accountList;
        if (username == null) {
            accountList = accountService.findAllAccounts();
        }
        else {
            Account account = accountService.findAccountByUsername(username);
            if (account == null) {
                accountList = new AccountList(new ArrayList<>());
            }
            else {
                accountList = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAssembler().toResource(accountList);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAssembler().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
        }
        catch (AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccount(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAssembler().toResource(account);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource res) {
        try {
            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
            BlogResource createdBlogRes = new BlogResourceAssembler().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<>(createdBlogRes, headers, HttpStatus.CREATED);
        }
        catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
        catch (BlogExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(@PathVariable Long accountId) {
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAssembler().toResource(blogList);
            return new ResponseEntity<>(blogListRes, HttpStatus.OK);
        }
        catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }

    @RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAccountsByRole(@PathVariable String role) {
        try {
            AccountList accountList = accountService.findAccountsByRole(role);
            AccountListResource accountListRes = new AccountListResourceAssembler().toResource(accountList);
            return new ResponseEntity<>(accountListRes, HttpStatus.OK);
        }
        catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }
}
