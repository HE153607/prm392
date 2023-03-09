package com.example.project_prm392_se1614.jwtutil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.User;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTUtil {
    private static final String KEY_TOKEN = "key_access_token";

    public static String GenToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(KEY_TOKEN);
        Date expiration = new Date(new Date().getTime() + (60 * 60 * 1000));//1 giờ

        String token = JWT.create()
                .withClaim("email", user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(expiration)
                .sign(algorithm);

        return token;
    }

    public static boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser().
                    setSigningKey(KEY_TOKEN).
                    parseClaimsJws(token).getBody();
            if(claims.getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User extractToken(String token) {
        Claims claims = Jwts.parser().
                setSigningKey(KEY_TOKEN).
                parseClaimsJws(token).getBody();

        User user = new User();

        user.setRole(Role.valueOf(claims.get("role").toString()));
        user.setEmail(claims.get("email").toString());
        user.setId(Integer.parseInt(claims.get("id").toString()));
        user.setName(claims.get("name").toString());

        return user;
    }

}
