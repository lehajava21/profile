package com.telran.mishpahug.model;

public class ServerLoginResponse {
    private String email;
    private String fullName;
    private String token;

    public ServerLoginResponse() {
    }

    public ServerLoginResponse(String email, String fullName, String token) {
        this.email = email;
        this.fullName = fullName;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
