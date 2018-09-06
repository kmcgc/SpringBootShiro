package com.stark.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.crypto.hash.Sha512Hash;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    private String salt;

    private Set<Role> roles;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.username = username;
        this.salt = getId().substring(0, 6);
        this.password = new Sha512Hash(password, getSalt()).toString();
        this.roles = new HashSet<>();
    }
}
