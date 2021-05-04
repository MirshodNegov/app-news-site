package uz.pdp.appnewssite.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "topSecret";

    public String generateToken(String username, Role role) {
        Date expireTime = new Date(System.currentTimeMillis() + JwtProvider.expireTime);
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .claim("roles", role.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();


    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (Exception e) {
            return null;
        }
    }


}
