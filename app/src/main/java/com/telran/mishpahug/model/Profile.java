package com.telran.mishpahug.model;

import java.io.Serializable;

public class Profile implements Serializable{

    private String token       = "";
    private String fullname    = "";
    private Boolean type       = false;
    private Boolean sex        = false;
    private String birthday    = "2000-1-1";
    private String[] photo     = {"","",""};
    private String address     = "";
    private Boolean marStatus  = false;
    private String kitchen     = "";
    private String religion    = "";
    private String kosher      = "";
    private String language    = "";
    private String about       = "";
    private String allergy     = "";

    public Profile() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String[] getPhoto() {return photo;}

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getMarStatus() {
        return marStatus;
    }

    public void setMarStatus(boolean marStatus) {
        this.marStatus = marStatus;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getKosher() {
        return kosher;
    }

    public void setKosher(String kosher) {
        this.kosher = kosher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
}
