package com.highrq.api.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class AccountListResource extends ResourceSupport {

    private List<AccountResource> accounts = new ArrayList<>();

    public List<AccountResource> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResource> accounts) {
        this.accounts = accounts;
    }
}
