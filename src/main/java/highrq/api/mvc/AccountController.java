package highrq.api.mvc;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.Blog;
import highrq.core.services.AccountService;
import highrq.core.services.exceptions.AccountDoesNotExistException;
import highrq.core.services.exceptions.AccountExistsException;
import highrq.core.services.exceptions.BlogExistsException;
import highrq.core.services.util.AccountList;
import highrq.core.services.util.BlogList;
import highrq.api.exceptions.ConflictException;
import highrq.api.exceptions.NotFoundException;
import highrq.api.resources.AccountListResource;
import highrq.api.resources.AccountResource;
import highrq.api.resources.BlogListResource;
import highrq.api.resources.BlogResource;
import highrq.api.resources.assemblers.AccountListResourceAssembler;
import highrq.api.resources.assemblers.AccountResourceAssembler;
import highrq.api.resources.assemblers.BlogListResourceAssembler;
import highrq.api.resources.assemblers.BlogResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "username", required = false) String username) {

        AccountList list = null;
        if (username == null) {
            list = accountService.findAllAccounts();
        }
        else {
            Account account = accountService.findAccountByUsername(username);
            if (account == null) {
                list = new AccountList(new ArrayList<Account>());
            }
            else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAssembler().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAssembler().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
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
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource res) {
        try {
            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
            BlogResource createdBlogRes = new BlogResourceAssembler().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
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
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
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
            return new ResponseEntity<AccountListResource>(accountListRes, HttpStatus.OK);
        }
        catch (AccountDoesNotExistException exception) {
            throw new NotFoundException(exception);
        }
    }
}
