package com.highrq.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {

  @Id
  @GeneratedValue
    private Long id;
    private String street;
    private String street2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address(String street, String street2, String city, String state, String zipCode, String country) {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, street2, city, state, zipCode, country);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return Objects.equals(this.street, other.street) && Objects.equals(this.street2, other.street2) && Objects.equals(this.city, other.city) && Objects.equals(this.state, other.state) && Objects.equals(this.zipCode, other.zipCode) && Objects.equals(this.country, other.country);
    }
}
