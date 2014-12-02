package highrq.rest.mvc;

import highrq.core.models.entities.Phone;
import highrq.core.services.PhoneService;
import highrq.core.services.exceptions.PhoneExistsException;
import highrq.rest.exceptions.ConflictException;
import highrq.rest.resources.PhoneResource;
import highrq.rest.resources.asm.PhoneResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@Controller
@RequestMapping("/rest/phones")
public class PhoneController {

  private PhoneService service;

  @Autowired
  public PhoneController(PhoneService phoneService) {
    this.service = phoneService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<PhoneResource> createPhone(@RequestBody PhoneResource sentPhone) {

    try {
      Phone createdPhone = service.createPhone(sentPhone.toPhone());
      PhoneResource res = new PhoneResourceAsm().toResource(createdPhone);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create(res.getLink("self").getHref()));
      return new ResponseEntity<PhoneResource>(res, headers, HttpStatus.CREATED);
    }
    catch (PhoneExistsException exception) {
      throw new ConflictException(exception);
    }
  }

  @RequestMapping(value = "/{phoneId}", method = RequestMethod.GET)
  public ResponseEntity<PhoneResource> getPhone(@PathVariable Long phoneId) {

    Phone phone = service.findPhone(phoneId);
    if (phone != null) {
      PhoneResource res = new PhoneResourceAsm().toResource(phone);
      return new ResponseEntity<PhoneResource>(res, HttpStatus.OK);
    } else {
      return new ResponseEntity<PhoneResource>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/{phoneId}", method = RequestMethod.DELETE)
  public ResponseEntity<PhoneResource> deletePhone(@PathVariable Long phoneId) {

    Phone phone = service.deletePhone(phoneId);
    if (phone != null) {
      PhoneResource resource = new PhoneResourceAsm().toResource(phone);
      return new ResponseEntity<PhoneResource>(resource, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<PhoneResource>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/{phoneId}", method = RequestMethod.PUT)
  public ResponseEntity<PhoneResource> updatePhone(@PathVariable Long phoneId, @RequestBody PhoneResource sentPhone) {

    Phone updatedEntry = service.updatePhone(sentPhone.toPhone());

    if (updatedEntry != null) {
      PhoneResource resource = new PhoneResourceAsm().toResource(updatedEntry);
      return new ResponseEntity<PhoneResource>(resource, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<PhoneResource>(HttpStatus.NOT_FOUND);
    }
  }

}

