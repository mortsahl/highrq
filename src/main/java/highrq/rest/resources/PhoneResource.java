package highrq.rest.resources;

import highrq.core.models.entities.Phone;
import org.springframework.hateoas.ResourceSupport;

public class PhoneResource extends ResourceSupport {

  private String areaCode;
  private String prefix;
  private String body;
  private String ext;

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getExt() {
    return ext;
  }

  public void setExt(String ext) {
    this.ext = ext;
  }

  public Phone toPhone() {
    Phone phone = new Phone();
    phone.setAreaCode(areaCode);
    phone.setPrefix(prefix);
    phone.setBody(body);
    phone.setExt(ext);
    return phone;
  }
}
