package cn.linkedcare.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Date;

/**
 * Created by Benji on 2018/8/3.
 */
public class TokenUtil {

    private static final int EXPIRE_TIME = 60 * 60 * 12 * 1000;
    private static final String issuer = "Tiamo";
    private static final String salt = "benji2018@linkedcare";


    public static String generateToken(String username) {
        String token = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(username)
                .setExpiration(new Date((System.currentTimeMillis() + EXPIRE_TIME)))
                .signWith(SignatureAlgorithm.HS512, salt)
                .compact();
        return token;
    }


    public static Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(salt)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        if (!claims.getExpiration().before(new Date())) {
            return claims;
        }
        return new DefaultClaims();
    }


}
