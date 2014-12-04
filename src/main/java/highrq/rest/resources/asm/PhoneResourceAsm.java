package highrq.rest.resources.asm;

import highrq.core.models.entities.Phone;
import highrq.rest.mvc.AccountController;
import highrq.rest.mvc.PhoneController;
import highrq.rest.resources.PhoneResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PhoneResourceAsm extends ResourceAssemblerSupport<Phone, PhoneResource> {

    public PhoneResourceAsm() {
        super(PhoneController.class, PhoneResource.class);
    }

    @Override
    public PhoneResource toResource(Phone phone) {
        PhoneResource resource = new PhoneResource();
        resource.setAreaCode(phone.getAreaCode());
        resource.setPrefix(phone.getPrefix());
        resource.setBody(phone.getBody());
        resource.setExt(phone.getExt());
        resource.setType(phone.getType());
        resource.setAccountId(phone.getAccountId());

        //  Link link =
        linkTo(PhoneController.class).slash(phone.getId()).withSelfRel();
        resource.add(linkTo(methodOn(PhoneController.class).getPhone(phone.getId())).withSelfRel());

        if (phone.getAccountId() != null) {
            resource.add(linkTo(AccountController.class).slash(phone.getAccountId()).withRel("owner"));
        }

        return resource;
    }
}
