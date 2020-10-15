package com.leo.springboot_shiro.config;

import com.leo.springboot_shiro.entity.User;
import com.leo.springboot_shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //资源控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findByUsername(principal);
        if ("mwq1".equals(user.getUsername())){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("user");
            info.addStringPermissions(Arrays.asList("user:add:people","user:update:people"));
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(principal);
        if (user!=null){
            return new SimpleAuthenticationInfo(principal,user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),getName());
        }
        return null;
    }


}
