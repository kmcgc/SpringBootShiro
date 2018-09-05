package com.stark.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm {


    /**
     * 用于判断登录信息的方法
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        //String password = String.valueOf(token.getPassword());
        if ("admin".equals(name)) {
            return new SimpleAuthenticationInfo(name, "123456", getName());
        } else {
            return null;
        }
    }

    /**
     * 这里是用来返回用户权限的方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
