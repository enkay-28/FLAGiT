package com.test.flagit;

public class NgoDetails {
    String name,ngoID,email,pass,address,mobile;

    public NgoDetails() {

    }

    public NgoDetails(String name, String ngoID, String email, String pass, String address, String mobile) {
        this.name = name;
        this.ngoID = ngoID;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgoID() {
        return ngoID;
    }

    public void setNgoID(String ngoID) {
        this.ngoID = ngoID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
