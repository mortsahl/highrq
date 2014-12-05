package highrq.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import highrq.core.models.entities.Account;


public class AccountResource extends ResourceSupport {
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setFname(fname);
        account.setLname(lname);
        account.setPassword(password);
        account.setRole(role);
        return account;
    }
}