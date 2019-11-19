package com.test.flagit;

public class Trafficker {
    String name,location,crime,img;
    boolean flag;
    Trafficker(){


    }

    public Trafficker(String name, String location, String crime, String img, boolean flag) {
        this.name = name;
        this.location = location;
        this.crime = crime;
        this.img = img;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
