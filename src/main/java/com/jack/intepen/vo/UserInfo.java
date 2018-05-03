package com.jack.intepen.vo;

import java.util.Set;

/**
 * Created by 11407 on 3/003.
 */
public class UserInfo {

    private Set<String> roles;
    private String token;
    private String avatar;
    private String name;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
