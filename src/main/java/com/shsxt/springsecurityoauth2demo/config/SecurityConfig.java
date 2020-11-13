package com.shsxt.springsecurityoauth2demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                //关闭csrf
                .disable()
                //授权配置
                .authorizeRequests()
                //放行
                .antMatchers("/oauth/**",
                        "/login/**","logout/**"
                        ,"/user/**")
                .permitAll()
                //剩下的所有全部要认证
                .anyRequest()
                .authenticated()
                //再次回到http的设置
                .and()
                .formLogin().permitAll();
    }

  @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
  }
}
