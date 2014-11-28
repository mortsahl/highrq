package highrq.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone {

  @Id
  @GeneratedValue
  private Long id;
  private String areaCode;
  private String prefix;
  private String body;
  private String ext;

  public Phone(String areaCode, String prefix, String body, String ext) {
    this.areaCode = areaCode;
    this.prefix = prefix;
    this.body = body;
    this.ext = ext;
  }

  public Phone(String areaCode, String prefix, String body) {
    this.areaCode = areaCode;
    this.prefix = prefix;
    this.body = body;
  }

  public Phone() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getFormattedPhoneNumber() {
    StringBuilder sb = new StringBuilder(16);
    sb = (sb.append('(').append(getAreaCode()).append(") ").append(getPrefix()).append("-").append(getBody()));
    if (org.apache.commons.lang3.StringUtils.isNotBlank(getExt())) {
      sb.append(" x").append(getExt());
    }
    return sb.toString();
  }
}
