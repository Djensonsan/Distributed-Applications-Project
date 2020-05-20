package dev.embeddables;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AddressEmbeddable implements Serializable {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int postalCode;
    private String country;

    public AddressEmbeddable() {
    }

    public AddressEmbeddable(String address1, String address2, String city, String state, int postalCode, String country) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
