package com.highrq.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.highrq.core.models.entities.Account;
import com.highrq.core.models.entities.Address;
import com.highrq.core.models.entities.Phone;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class AccountResource extends ResourceSupport {

    private String username;
    private String password;
    private String fname;
    private String lname;
    private String role;
    private List<Phone> phones;
    private Address address;

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setFname(fname);
        account.setLname(lname);
        account.setPassword(password);
        account.setRole(role);
        account.setPhones(phones);
        account.setAddress(address);
        return account;
    }
}
