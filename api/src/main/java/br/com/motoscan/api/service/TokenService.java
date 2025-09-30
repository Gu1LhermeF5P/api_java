package br.com.motoscan.api.service;

import br.com.motoscan.api.config.JwtProperties; // Importe
import br.com.motoscan.api.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired; // Importe
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Injetamos a classe de propriedades inteira
    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date now = new Date();
        // Usamos os getters da nossa classe de propriedades
        Date expirationDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .setIssuer("MotoScan API")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUsuarioId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
    
    private Key getSigningKey() {
        // Usamos os getters da nossa classe de propriedades
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}