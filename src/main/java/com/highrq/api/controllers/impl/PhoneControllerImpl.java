package com.highrq.api.controllers.impl;

import com.highrq.api.controllers.PhoneController;
import com.highrq.api.exceptions.ConflictException;
import com.highrq.api.resources.PhoneResource;
import com.highrq.api.resources.assemblers.PhoneResourceAssembler;
import com.highrq.core.models.entities.Phone;
import com.highrq.core.services.PhoneService;
import com.highrq.core.services.exceptions.PhoneExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/phones")
public class PhoneControllerImpl implements PhoneController {

    private PhoneService service;

    @Autowired
    public PhoneControllerImpl(PhoneService phoneService) {

        this.service = phoneService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PhoneResource> createPhone(@RequestBody PhoneResource sentPhone) {

        try {
            Phone createdPhone = service.createPhone(sentPhone.toPhone());
            PhoneResource res = new PhoneResourceAssembler().toResource(createdPhone);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
        }
        catch (PhoneExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.GET)
    public ResponseEntity<PhoneResource> getPhone(@PathVariable Long phoneId) {

        Phone phone = service.findPhone(phoneId);
        if (phone != null) {
            PhoneResource res = new PhoneResourceAssembler().toResource(phone);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.DELETE)
    public ResponseEntity<PhoneResource> deletePhone(@PathVariable Long phoneId) {

        Phone phone = service.deletePhone(phoneId);
        if (phone != null) {
            PhoneResource resource = new PhoneResourceAssembler().toResource(phone);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.PUT)
    public ResponseEntity<PhoneResource> updatePhone(@PathVariable Long phoneId, @RequestBody PhoneResource sentPhone) {

        Phone updatedEntry = service.updatePhone(sentPhone.toPhone());

        if (updatedEntry != null) {
            PhoneResource resource = new PhoneResourceAssembler().toResource(updatedEntry);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

