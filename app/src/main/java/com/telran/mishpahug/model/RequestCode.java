package com.telran.mishpahug.model;

public enum RequestCode {
    PHOTO(1);

    Integer id;

    RequestCode(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
