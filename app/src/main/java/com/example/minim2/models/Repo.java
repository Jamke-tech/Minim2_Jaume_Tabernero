package com.example.minim2.models;

import java.io.Serializable;

public class Repo implements Serializable {

    public String name;
    public String language;

    public Repo(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
