package highrq.api.resources;

import highrq.core.models.entities.Phone;
import org.springframework.hateoas.ResourceSupport;

public class PhoneResource extends ResourceSupport {

  private String areaCode;
  private String prefix;
  private String body;
  private String ext;
  private String type;
  private Long accountId;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Phone toPhone() {
    Phone phone = new Phone();
    phone.setAreaCode(areaCode);
    phone.setPrefix(prefix);
    phone.setBody(body);
    phone.setExt(ext);
    phone.setType(type);
    phone.setAccountId(accountId);

    return phone;
  }
}
