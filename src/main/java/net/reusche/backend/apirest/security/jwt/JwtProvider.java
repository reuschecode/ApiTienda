package net.reusche.backend.apirest.security.jwt;

import io.jsonwebtoken.*;
import net.reusche.backend.apirest.security.entity.SuperAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication auth){
        SuperAdmin superAdmin = (SuperAdmin) auth.getPrincipal();
        return Jwts.builder().setSubject(superAdmin.getUsername())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException ex){
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException ex){
            logger.error("Token no soportado");
        }catch (ExpiredJwtException ex){
            logger.error("Token expirado");
        }catch (IllegalArgumentException ex){
            logger.error("Token vacío");
        }catch (SignatureException ex){
            logger.error("Fail en la firma");
        }
        return false;
    }
}
