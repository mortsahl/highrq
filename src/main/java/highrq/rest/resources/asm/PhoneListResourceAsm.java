package highrq.rest.resources.asm;

import highrq.core.services.util.PhoneList;
import highrq.rest.mvc.PhoneController;
import highrq.rest.resources.PhoneListResource;
import highrq.rest.resources.PhoneResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

public class PhoneListResourceAsm extends ResourceAssemblerSupport<PhoneList, PhoneListResource> {

    public PhoneListResourceAsm() {
        super(PhoneController.class, PhoneListResource.class);
    }

    @Override
    public PhoneListResource toResource(PhoneList phoneList) {
        List<PhoneResource> phoneResourceList = new PhoneResourceAsm().toResources(phoneList.getPhones());
        PhoneListResource phoneListResource = new PhoneListResource();
        phoneListResource.setPhones(phoneResourceList);
        return phoneListResource;
    }
}



