package com.leo.shiro_thymeleaf.config;

import com.leo.shiro_thymeleaf.entity.MyByteSource;
import com.leo.shiro_thymeleaf.entity.User;
import com.leo.shiro_thymeleaf.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //资源控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入到授权");
        String principal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findByUsername(principal);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        user.getRoles().forEach(
                r -> {
                    info.addRole(r.getName());
                    r.getPermissions().forEach(
                            p -> info.addStringPermission(p.getPermission())
                    );
                }
        );
//        假的模拟数据
//        if ("mwq1".equals(user.getUsername())){
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            info.addRole("user");
//            info.addStringPermissions(Arrays.asList("user:add:people","user:update:people"));
//            return info;
//        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入到认证");
        String principal = (String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(principal);
        if (user != null) {
            return new SimpleAuthenticationInfo(principal, user.getPassword(),
                    new MyByteSource(user.getSalt()), getName());
        }
        return null;
    }


}
