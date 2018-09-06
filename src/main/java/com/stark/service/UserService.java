package com.stark.service;

import com.stark.entity.Permission;
import com.stark.entity.Role;
import com.stark.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("user", new User("user", "123456"));
        userMap.put("admin", new User("admin", "123456"));
        userMap.put("super", new User("super", "123456"));

        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        Role superRole = new Role("super");

        Permission getPermission = new Permission("get");
        Permission addPermission = new Permission("add");
        adminRole.getPermissions().add(getPermission);
        superRole.getPermissions().add(getPermission);
        superRole.getPermissions().add(addPermission);


        userMap.get("user").getRoles().add(userRole);
        userMap.get("admin").getRoles().add(userRole);
        userMap.get("admin").getRoles().add(adminRole);
        userMap.get("super").getRoles().add(userRole);
        userMap.get("super").getRoles().add(adminRole);
        userMap.get("super").getRoles().add(superRole);
    }

    public static User getUserByName(String name) {
        return userMap.get(name);
    }
}

