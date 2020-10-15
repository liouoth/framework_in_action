package com.leo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * 自定义realm可以将realm自定义，比如使用数据库
 */
public class CustomMd5Realm extends AuthorizingRealm {

    //授权
    //每次调用权限isPermitted 还有 hasRole 就授权一遍
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        principalCollection.getPrimaryPrincipal(); //获取到用户principal，然后根据这个principal获取权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        //背地里是一个hashset
        //添加权限方式，添加角色，添加权限字符串
        simpleAuthorizationInfo.addRoles(Arrays.asList("admin","admin"));
        simpleAuthorizationInfo.addStringPermission("user:*:001");
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        if ("xiaomao".equals(principal)) {
            //用户输入密码 盐值 hash次数
            //从数据库中取出，相同添加添加盐值加密的密码
            //因为controller层配置了hash的自动匹配，所以需要把盐值返回回去
            //可以通过set方法 或者通过构造器返回
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("xiaomao",
                    "595b41802d6effccc1f7ab1e21562528",ByteSource.Util.bytes("hsjdja")
                    , this.getName());
//            info.setCredentialsSalt(ByteSource.Util.bytes("hsjdja"));
            return info;
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("1234","hsjdja",1024);
        String passwd = md5Hash.toHex();
        System.out.println(passwd);
    }
}
