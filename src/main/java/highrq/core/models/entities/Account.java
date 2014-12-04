package highrq.core.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findAllAccounts", query = "SELECT a FROM Account a"),
        @NamedQuery(name = "Account.findAccountByUsername", query = "SELECT a FROM Account a WHERE a.username=?1"),
        @NamedQuery(name = "Account.findAccountsByRole", query = "SELECT a FROM Account a WHERE a.role=?1")
})
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role;

    // TODO - sja: Need firstname/lastname"

    public Account() {}
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && Objects.equals(this.password, other.password) && Objects.equals(this.role, other.role);
    }
}
