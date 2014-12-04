package highrq.rest.resources.assemblers;

import highrq.core.models.entities.Account;
import highrq.rest.mvc.AccountController;
import highrq.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAssembler() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setName(account.getUsername());
        resource.setPassword(account.getPassword());
        resource.setRole((account.getRole()));
        resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withRel("blogs"));
        return resource;
    }
}
