package highrq.core.services.impl;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.Blog;
import highrq.core.repositories.AccountDAO;
import highrq.core.repositories.BlogDAO;
import highrq.core.services.AccountService;
import highrq.core.services.exceptions.AccountDoesNotExistException;
import highrq.core.services.exceptions.AccountExistsException;
import highrq.core.services.exceptions.BlogExistsException;
import highrq.core.services.util.AccountList;
import highrq.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BlogDAO blogDAO;

    @Override
    public Account findAccount(Long id) {
        return accountDAO.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountDAO.findAccountByName(data.getName());
        if (account != null) {
            throw new AccountExistsException();
        }
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
    public Account findByAccountName(String name) {
        return accountDAO.findAccountByName(name);
    }

    @Override
    public AccountList findAccountsByRole(String role) {
        return new AccountList(accountDAO.findAccountsByRole(role));
    }

}

