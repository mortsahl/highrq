package com.highrq.api.resources.assemblers;

import com.highrq.api.controllers.impl.PhoneControllerImpl;
import com.highrq.api.resources.PhoneResource;
import com.highrq.core.models.entities.Phone;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PhoneResourceAssembler extends ResourceAssemblerSupport<Phone, PhoneResource> {

    public PhoneResourceAssembler() {
        super(PhoneControllerImpl.class, PhoneResource.class);
    }

    @Override
    public PhoneResource toResource(Phone phone) {
        PhoneResource phoneResource = new PhoneResource();
        phoneResource.setAreacode(phone.getAreacode());
        phoneResource.setPrefix(phone.getPrefix());
        phoneResource.setBody(phone.getBody());
        phoneResource.setExt(phone.getExt());
        phoneResource.setType(phone.getType());

        //  Link link =
        linkTo(PhoneControllerImpl.class).slash(phone.getId()).withSelfRel();
        phoneResource.add(linkTo(methodOn(PhoneControllerImpl.class).getPhone(phone.getId())).withSelfRel());

        return phoneResource;
    }
}
