package highrq.core.services.impl;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.Address;
import highrq.core.models.entities.Blog;
import highrq.core.models.entities.Phone;
import highrq.core.repositories.AccountDAO;
import highrq.core.repositories.BlogDAO;
import highrq.core.repositories.PhoneDAO;
import highrq.core.services.AccountService;
import highrq.core.services.exceptions.AccountDoesNotExistException;
import highrq.core.services.exceptions.AccountExistsException;
import highrq.core.services.exceptions.BlogExistsException;
import highrq.core.services.util.AccountList;
import highrq.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private PhoneDAO phoneDao;

    @Autowired
    private BlogDAO blogDAO;

    @Override
    public Account findAccount(Long id) {
        return accountDAO.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountDAO.findAccountByUsername(data.getUsername());
        if (account != null) {
            throw new AccountExistsException();
        }
        List<Phone> phones = new ArrayList<>();
        for (Phone phone : data.getPhones()) {
            phones.add(phone);
        }
        data.setPhones(phones);

        return accountDAO.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogSameTitle = blogDAO.findBlogByTitle(data.getTitle());

        if (blogSameTitle != null) {
            throw new BlogExistsException();
        }

        Account account = accountDAO.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        Blog createdBlog = blogDAO.createBlog(data);
        createdBlog.setAccount(account);
        return createdBlog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountDAO.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogDAO.findBlogsByAccount(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountDAO.findAllAccounts());
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountDAO.findAccountByUsername(username);
    }

    @Override
    public AccountList findAccountsByRole(String role) {
        return new AccountList(accountDAO.findAccountsByRole(role));
    }

    // TODO - sja: Implement me
    @Override
    public Account updateAccountPhone(Phone phone) {
        return null;
    }

    // TODO - sja: Implement me
    @Override
    public Account updateAccountAddress(Address address) {
        return null;
    }

    // TODO - sja: Implement me
    @Override
    public Account addAccountPhone(Phone phone) {
        return null;
    }

    // TODO - sja: Implement me
    @Override
    public Account deleteAccountPhone(Long phoneId) {
        return null;
    }
}

