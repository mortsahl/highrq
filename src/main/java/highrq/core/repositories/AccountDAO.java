package highrq.core.repositories;

import highrq.core.models.entities.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllAccounts();
    public List<Account> findAccountsByRole(String role);
    public Account findAccount(Long id);
    public Account findAccountByName(String name);
    public Account createAccount(Account data);
}
