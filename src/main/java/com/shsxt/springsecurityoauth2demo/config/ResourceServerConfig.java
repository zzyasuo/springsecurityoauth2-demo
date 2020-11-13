package com.shsxt.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //所有的请求都要被认证才能访问
                .anyRequest().authenticated()
                .and()
                //相当于有令牌之后才能访问
                .requestMatchers()
                .antMatchers("/user/**");
    }
}
