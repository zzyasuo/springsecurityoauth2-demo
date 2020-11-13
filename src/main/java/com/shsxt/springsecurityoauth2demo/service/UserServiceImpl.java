package com.shsxt.springsecurityoauth2demo.service;

import com.shsxt.springsecurityoauth2demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Resource
    private PasswordEncoder pw;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin",pw.encode("123456"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
