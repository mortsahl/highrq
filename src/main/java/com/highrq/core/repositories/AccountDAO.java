package com.highrq.core.repositories;

import com.highrq.core.models.entities.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> findAllAccounts();
    public List<Account> findAccountsByRole(String role);
    public Account findAccount(Long id);
    public Account findAccountByUsername(String username);
    public Account createAccount(Account data);
}
