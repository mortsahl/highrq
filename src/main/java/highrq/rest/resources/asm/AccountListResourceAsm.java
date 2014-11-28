package highrq.rest.resources.asm;

import highrq.rest.resources.AccountListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.services.util.AccountList;
import highrq.rest.mvc.AccountController;
import highrq.rest.resources.AccountResource;

import java.util.List;


public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
