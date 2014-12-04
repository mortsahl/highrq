package highrq.rest.resources.asm;

import highrq.rest.mvc.AccountController;
import highrq.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import highrq.core.models.entities.Account;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setName(account.getName());
        resource.setPassword(account.getPassword());
        resource.setRole((account.getRole()));
        resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withRel("blogs"));
        return resource;
    }
}
