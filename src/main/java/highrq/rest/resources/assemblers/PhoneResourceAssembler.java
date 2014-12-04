package highrq.rest.resources.assemblers;

import highrq.core.models.entities.Phone;
import highrq.rest.mvc.AccountController;
import highrq.rest.mvc.PhoneController;
import highrq.rest.resources.PhoneResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PhoneResourceAssembler extends ResourceAssemblerSupport<Phone, PhoneResource> {

    public PhoneResourceAssembler() {
        super(PhoneController.class, PhoneResource.class);
    }

    @Override
    public PhoneResource toResource(Phone phone) {
        PhoneResource phoneResource = new PhoneResource();
        phoneResource.setAreaCode(phone.getAreaCode());
        phoneResource.setPrefix(phone.getPrefix());
        phoneResource.setBody(phone.getBody());
        phoneResource.setExt(phone.getExt());
        phoneResource.setType(phone.getType());
        phoneResource.setAccountId(phone.getAccountId());

        //  Link link =
        linkTo(PhoneController.class).slash(phone.getId()).withSelfRel();
        phoneResource.add(linkTo(methodOn(PhoneController.class).getPhone(phone.getId())).withSelfRel());

        if (phone.getAccountId() != null) {
            phoneResource.add(linkTo(AccountController.class).slash(phone.getAccountId()).withRel("owner"));
        }

        return phoneResource;
    }
}
