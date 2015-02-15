package com.highrq.core.services.util;

import com.highrq.core.models.entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneList {

    private List<Phone> phones = new ArrayList<>();

    public PhoneList(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
