package com.stark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Role implements Serializable {
    private String name;

    private Set<Permission> permissions;

    public Role(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
    }
}
