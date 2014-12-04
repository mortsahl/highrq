package highrq.rest.resources.assemblers;

import highrq.rest.resources.AccountListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.services.util.AccountList;
import highrq.rest.mvc.AccountController;
import highrq.rest.resources.AccountResource;

import java.util.List;


public class AccountListResourceAssembler extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    public AccountListResourceAssembler() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> accountResourceList = new AccountResourceAssembler().toResources(accountList.getAccounts());
        AccountListResource accountListResource = new AccountListResource();
        accountListResource.setAccounts(accountResourceList);
        return accountListResource;
    }
}
