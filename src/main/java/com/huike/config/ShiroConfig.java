package com.huike.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//和我一样的好老师
@Configuration
public class ShiroConfig {
    //3.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("webSecurityManager") DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(webSecurityManager);
        //配置很多过滤器
        /*
        * anon: 不认证也能访问
        * authc：认证之后才能访问
        * perms: 有某个权限才能访问
        * */
        Map<String,String> map = new HashMap<>();
        map.put("/user/login","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/img/**","anon");

        /*map.put("/add","perms[user:add]");
        map.put("/update","perms[user:update]");
        map.put("/select","perms[user:select]");
        map.put("/delete","perms[user:delete]");*/
        map.put("/**","authc");

        factoryBean.setLoginUrl("/login.html"); //设置登录地址（当访问需要认证的资源但还未认证时，需要跳转到登录页面）
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }


    //2.DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager webSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager(userRealm);
        return webSecurityManager;
    }
    //1.UserRealm
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
