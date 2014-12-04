package highrq.core.services.util;

import highrq.core.models.entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneList {

    private List<Phone> phones = new ArrayList<Phone>();

    public PhoneList(List<Phone> list) {
        this.phones = list;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> accounts) {
        this.phones = phones;
    }
}
