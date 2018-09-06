package com.stark.service;

import com.stark.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("admin1", new User("admin1", "123456"));
        userMap.put("admin2", new User("admin2", "123456"));
    }

    public static User getUserByName(String name) {
        return userMap.get(name);
    }
}

