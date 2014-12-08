package highrq.core.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone")
@NamedQueries({
        @NamedQuery(name = "Phone.findPhonesByAreacode", query = "Select p from Phone p where p.areacode=:areacode")
})

public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String areacode;
    private String prefix;
    private String body;
    private String ext;
    private String type;

    public Phone(String areacode, String prefix, String body, String ext, String type) {
        this.areacode = areacode;
        this.prefix = prefix;
        this.body = body;
        this.ext = ext;
        this.type = type;
    }

    public Phone(String areacode, String prefix, String body, String type) {
        this.areacode = areacode;
        this.prefix = prefix;
        this.body = body;
        this.type = type;
    }

    public Phone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getFormattedPhoneNumber() {
        StringBuilder sb = new StringBuilder(16);
        sb = (sb.append('(').append(getAreacode()).append(") ").append(getPrefix()).append("-").append(getBody()));
        if (org.apache.commons.lang3.StringUtils.isNotBlank(getExt())) {
            sb.append(" x").append(getExt());
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areacode, prefix, body, ext, type);
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
        return Objects.equals(this.id, other.id) && Objects.equals(this.areacode, other.areacode) && Objects.equals(this.prefix, other.prefix) && Objects.equals(this.body, other.body) && Objects.equals(this.ext, other.ext) && Objects.equals(this.type, other.type);
    }
}
