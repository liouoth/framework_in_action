package com.leo.shiro_thymeleaf.config;

import com.leo.shiro_thymeleaf.cache.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//shiro整合配置类
@Configuration
public class ShiroConfig {
    @Autowired
    private RedisCacheManager redisCacheManager;

    //shiro filter
    //注入
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置受限资源
        //登录页面会默认去/login
        //登录退出页面会默认去/logout
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/login", "anon");//将不受限制的放在上面

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        shiroFilterFactoryBean.setLoginUrl();
        return shiroFilterFactoryBean;
    }

    //创建security manager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm customerRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customerRealm);
        return defaultWebSecurityManager;
    }

    //shiro realm
    @Bean
    public Realm customerRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        //设置缓存，开启缓存管理
        //ehchache
        customerRealm.setCacheManager(redisCacheManager);
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("AuthenticationCacheName");
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("AuthorizationCacheName");
        customerRealm.setCachingEnabled(true);
        return customerRealm;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
