package com.example.minim2.models;

import java.io.Serializable;

public class User implements Serializable {

    private static User userinstance;

    public String login;
    public String avatar_url;
    public String repos_url;
    public int followers;
    public int following;

    public User(){}

    public static synchronized User getInstance(){
        if(userinstance == null) {
            userinstance = new User();
        }
        return userinstance;
    }

    public User(String login, String avatar_url, String repos_url, int followers, int following) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.repos_url = repos_url;
        this.followers = followers;
        this.following = following;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
