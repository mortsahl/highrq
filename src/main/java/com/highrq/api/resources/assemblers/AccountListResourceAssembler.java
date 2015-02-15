package com.highrq.api.resources.assemblers;

import com.highrq.core.services.util.AccountList;
import com.highrq.api.controllers.impl.AccountControllerImpl;
import com.highrq.api.resources.AccountListResource;
import com.highrq.api.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountListResourceAssembler extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    public AccountListResourceAssembler() {
        super(AccountControllerImpl.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> accountResourceList = new AccountResourceAssembler().toResources(accountList.getAccounts());
        AccountListResource accountListResource = new AccountListResource();
        accountListResource.setAccounts(accountResourceList);
        return accountListResource;
    }
}
