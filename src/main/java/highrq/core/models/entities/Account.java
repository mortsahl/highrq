package highrq.core.models.entities;

import javax.persistence.*;


@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findAllAccounts", query = "SELECT a FROM Account a"),
        @NamedQuery(name = "Account.findAccountByName", query = "SELECT a FROM Account a WHERE a.name=?1")
})
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
