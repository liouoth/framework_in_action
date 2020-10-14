package com.leo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm可以将realm自定义，比如使用数据库
 */
public class CustomRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        if ("xiaomao".equals(principal)) {
            //args 用户名 密码 还有realm realm是自动获取的，使用父类方法
            //返回数据库的内容，如果数据库返回ingo principal和传入token的这个不一样.还是成功
            //因为doGetAuthenticationInfo 需要校验principal 后续只会校验credentials
            return new SimpleAuthenticationInfo("xiaomao11","1234",this.getName());
        }
        return null;
    }
}
