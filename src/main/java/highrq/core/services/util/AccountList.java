package highrq.core.services.util;

import highrq.core.models.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountList {

    private List<Account> accounts = new ArrayList<>();

    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
