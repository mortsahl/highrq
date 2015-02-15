package com.highrq.api.controllers;

import com.highrq.api.resources.PhoneResource;
import org.springframework.http.ResponseEntity;

public interface PhoneController {

    public ResponseEntity<PhoneResource> createPhone(PhoneResource sentPhone);
    public ResponseEntity<PhoneResource> getPhone(Long phoneId);
    public ResponseEntity<PhoneResource> deletePhone(Long phoneId);
    public ResponseEntity<PhoneResource> updatePhone(Long phoneId, PhoneResource sentPhone);

    // TODO - sja: Need findPhonesByAccount

    // TODO - sja: Need findPhonesByAreacode
}
