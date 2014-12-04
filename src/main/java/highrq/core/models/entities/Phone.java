package highrq.core.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone")
@NamedQueries({
        @NamedQuery(name = "Phone.findPhonesByAreaCode", query = "Select p from Phone p where p.areaCode=:areaCode"),
        @NamedQuery(name = "Phone.findPhonesByAccount", query = "Select p, a from Phone p, Account a where a.id = p.accountId")
})

public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String areaCode;
    private String prefix;
    private String body;
    private String ext;
    private String type;
    private Long accountId;

    public Phone(String areaCode, String prefix, String body, String ext, String type, Long accountId) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.body = body;
        this.ext = ext;
        this.type = type;
        this.accountId = accountId;
    }

    public Phone(String areaCode, String prefix, String body, String type, Long accountId) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.body = body;
        this.type = type;
        this.accountId = accountId;
    }

    public Phone() {
    }

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

    public String getType() {
        return type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getFormattedPhoneNumber() {
        StringBuilder sb = new StringBuilder(16);
        sb = (sb.append('(').append(getAreaCode()).append(") ").append(getPrefix()).append("-").append(getBody()));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(getExt())) {
            sb.append(" x").append(getExt());
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaCode, prefix, body, ext, type, accountId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Phone other = (Phone) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.areaCode, other.areaCode) && Objects.equals(this.prefix, other.prefix) && Objects.equals(this.body, other.body) && Objects.equals(this.ext, other.ext) && Objects.equals(this.type, other.type) && Objects.equals(this.accountId, other.accountId);
    }
}
