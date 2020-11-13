package com.shsxt.springsecurityoauth2demo.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 资源
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request){

        String token = request.getHeader(
                "Authorization");
        String jwtToken = token.substring(token.indexOf("Bearer" )+ 7);
        System.out.println(jwtToken);


        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(jwtToken)
                .getBody();
        System.out.println(claims.getId());
        return claims;
    }
}
