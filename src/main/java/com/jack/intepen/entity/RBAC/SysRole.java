package com.jack.intepen.entity.RBAC;

import com.jack.intepen.dto.IntepenResult;

/**
 * Created by 11407 on 2/002.
 */
public class SysRole {

    private Integer id;
    private String role;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
