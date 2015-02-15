package com.highrq.api.resources.assemblers;

import com.highrq.api.controllers.impl.AccountControllerImpl;
import com.highrq.api.resources.AccountResource;
import com.highrq.core.models.entities.Account;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAssembler() {
        super(AccountControllerImpl.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setUsername(account.getUsername());
        resource.setFname(account.getFname());
        resource.setLname(account.getLname());
        resource.setPassword(account.getPassword());
        resource.setRole((account.getRole()));
        resource.setPhones(account.getPhones());
        resource.setAddress(account.getAddress());
        resource.add(linkTo(methodOn(AccountControllerImpl.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountControllerImpl.class).findAllBlogs(account.getId())).withRel("blogs"));

        // TODO - sja: add phones here?

        return resource;
    }
}
