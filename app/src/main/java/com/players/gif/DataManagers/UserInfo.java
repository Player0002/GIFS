package com.players.gif.DataManagers;

import java.util.ArrayList;
import java.util.Date;

public class UserInfo {
    private String username;
    private String email;
    private Date when;
    private String profileImgName;
    private boolean isGoogle;
    private ArrayList<Long> groups;

    // GETTERS
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getWhen() {
        return when;
    }

    public String getProfileImgName() {
        return profileImgName;
    }

    public boolean isGoogle() {
        return isGoogle;
    }

    public ArrayList<Long> getGroups() {
        return groups;
    }

    public static UserInfo getUserinfo() {
        return userinfo;
    }

    //SETTERS
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public void setProfileImgName(String profileImgName) {
        this.profileImgName = profileImgName;
    }

    public void setGoogle(boolean google) {
        isGoogle = google;
    }

    public void setGroups(ArrayList<Long> groups) {
        this.groups = groups;
    }

    public static void setUserinfo(UserInfo userinfo) {
        UserInfo.userinfo = userinfo;
    }

    private static UserInfo userinfo;

    public static UserInfo getInstance(){
        if(userinfo == null) userinfo = new UserInfo();
        return userinfo;
    }
}
