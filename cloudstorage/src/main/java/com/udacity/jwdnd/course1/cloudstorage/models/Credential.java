package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {


    private Integer credentialId;
    private String url;
    private String userName;
    private String password;

    private User userId;

    public Credential() {
    }

    public Credential(Integer credentialId, String url, String userName, String password, User userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}