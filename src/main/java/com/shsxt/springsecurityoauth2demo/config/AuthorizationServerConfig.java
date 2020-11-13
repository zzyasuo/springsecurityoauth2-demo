package com.shsxt.springsecurityoauth2demo.config;


import com.shsxt.springsecurityoauth2demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * 授权服务器配置类
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder pw;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    @Qualifier("jwtTokenStore")
//    @Resource
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 密码模式的配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //自定义登录逻辑
                .userDetailsService(userService)
                .tokenStore(tokenStore)
                //accessToken转换成jwt
                .accessTokenConverter(jwtAccessTokenConverter);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //基于内存
        clients.inMemory()
                //clientId
                .withClient("admin")
                .secret(pw.encode("112233"))
                //重定向url，为了获取授权码
                .redirectUris("http://www.baidu.com")
                //授权范围
                .scopes("all")
                //授权类型：授权码,密码
                .authorizedGrantTypes("authorization_code","password");
    }
}
