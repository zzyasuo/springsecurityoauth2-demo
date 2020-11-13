package com.shsxt.springsecurityoauth2demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springsecurityoauth2DemoApplicationTests {

    @Test
    void contextLoads() {

        String token  =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDUzMTUzODYsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiOGU2N2Y1ZDEtYTNlYy00OWIxLTk1MDQtOGM4MGNhZjEyYTA5IiwiY2xpZW50X2lkIjoiYWRtaW4iLCJzY29wZSI6WyJhbGwiXX0.OhcppTVnZhbWglcSW4bUgAKYToGtUFx5mIFNMgKpM_M";

        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims.getId());
    }

    @Test
    void contextLoads1() {

        String compact = Jwts.builder()
                .claim("a", "aa")
                .signWith(SignatureAlgorithm.HS256, "abcd")
                .compact();

        System.out.println(compact);
    }

}
