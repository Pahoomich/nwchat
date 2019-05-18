package com.nwchat.model;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="role_id")
    private int role_id;

    @Column(name="role")
    private String role;

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int id) {
        this.role_id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
