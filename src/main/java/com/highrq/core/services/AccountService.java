package com.highrq.core.services;

import com.highrq.core.models.entities.Account;
import com.highrq.core.models.entities.Address;
import com.highrq.core.models.entities.Blog;
import com.highrq.core.models.entities.Phone;
import com.highrq.core.services.util.AccountList;
import com.highrq.core.services.util.BlogList;


public interface AccountService {
    public AccountList findAllAccounts();
    public Account findAccount(Long accountId);
    public Account createAccount(Account account);
    public Account findAccountByUsername(String username);
    public AccountList findAccountsByRole(String role);

    public Blog createBlog(Long accountId, Blog blog);
    public BlogList findBlogsByAccount(Long accountId);

    public Account updateAccountPhone(Phone phone);
    public Account addAccountPhone(Phone phone);
    public Account deleteAccountPhone(Long phoneId);

    public Account updateAccountAddress(Address address);
}
