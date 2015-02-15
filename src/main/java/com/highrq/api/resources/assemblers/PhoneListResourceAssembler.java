package com.highrq.api.resources.assemblers;

import com.highrq.core.services.util.PhoneList;
import com.highrq.api.controllers.PhoneController;
import com.highrq.api.resources.PhoneListResource;
import com.highrq.api.resources.PhoneResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneListResourceAssembler extends ResourceAssemblerSupport<PhoneList, PhoneListResource> {

    public PhoneListResourceAssembler() {
        super(PhoneController.class, PhoneListResource.class);
    }

    @Override
    public PhoneListResource toResource(PhoneList phoneList) {
        List<PhoneResource> phoneResourceList = new PhoneResourceAssembler().toResources(phoneList.getPhones());
        PhoneListResource phoneListResource = new PhoneListResource();
        phoneListResource.setPhones(phoneResourceList);
        return phoneListResource;
    }
}



