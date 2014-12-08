package highrq.api.resources.assemblers;

import highrq.api.mvc.AccountController;
import highrq.api.resources.AccountResource;
import highrq.core.models.entities.Account;
import highrq.core.models.entities.Phone;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

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
        resource.setUsername(account.getUsername());
        resource.setFname(account.getFname());
        resource.setLname(account.getLname());
        resource.setPassword(account.getPassword());
        resource.setRole((account.getRole()));
        resource.setPhones((List<Phone>) account.getPhones());
        resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withRel("blogs"));
        return resource;
    }
}
