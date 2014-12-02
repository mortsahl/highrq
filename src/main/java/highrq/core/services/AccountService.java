package highrq.core.services;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.Blog;
import highrq.core.services.util.AccountList;
import highrq.core.services.util.BlogList;


public interface AccountService {
    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
    public AccountList findAccountsByRole(String role);
}
