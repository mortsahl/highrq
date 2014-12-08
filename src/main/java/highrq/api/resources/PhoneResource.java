package highrq.api.resources;

import highrq.core.models.entities.Account;
import highrq.core.models.entities.Phone;
import org.springframework.hateoas.ResourceSupport;

public class PhoneResource extends ResourceSupport {

  private String areacode;
  private String prefix;
  private String body;
  private String ext;
  private String type;
  private Account account;

  public String getAreacode() {
    return areacode;
  }

  public void setAreacode(String areacode) {
    this.areacode = areacode;
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

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Phone toPhone() {
    Phone phone = new Phone();
    phone.setAreacode(areacode);
    phone.setPrefix(prefix);
    phone.setBody(body);
    phone.setExt(ext);
    phone.setType(type);
    phone.setAccount(account);

    return phone;
  }
}
