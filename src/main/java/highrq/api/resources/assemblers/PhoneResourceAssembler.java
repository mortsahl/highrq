package highrq.api.resources.assemblers;

import highrq.api.mvc.PhoneController;
import highrq.api.resources.PhoneResource;
import highrq.core.models.entities.Phone;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
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

        //  Link link =
        linkTo(PhoneController.class).slash(phone.getId()).withSelfRel();
        phoneResource.add(linkTo(methodOn(PhoneController.class).getPhone(phone.getId())).withSelfRel());

        return phoneResource;
    }
}
