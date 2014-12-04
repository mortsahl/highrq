package highrq.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class PhoneListResource extends ResourceSupport {

    private List<PhoneResource> phones = new ArrayList<PhoneResource>();

    public List<PhoneResource> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneResource> phones) {
        this.phones = phones;
    }
}
