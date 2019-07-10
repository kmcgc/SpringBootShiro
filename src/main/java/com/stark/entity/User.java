package com.stark.entity;

import org.apache.shiro.crypto.hash.Sha512Hash;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    private String salt;

    private boolean locked;

    private Set<Role> roles;

    public User(String username, String password, boolean locked) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.username = username;
        this.salt = getId().substring(0, 6);
        this.password = new Sha512Hash(password, getSalt()).toString();
        this.roles = new HashSet<>();
        this.locked = locked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
