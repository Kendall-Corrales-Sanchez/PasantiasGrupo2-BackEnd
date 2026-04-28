package pasantia.backend.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import pasantia.backend.entity.Users;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta, debe ser larga y segura
    private static final String SECRET_KEY = "miClaveSecretaSuperLargaParaJWT1234567890ABCDabcd";

    // Generar token
    public String generateToken(Users users) {
        return Jwts.builder()
                .subject(users.getMail())
                .claim("id", users.getId())
                .claim("name", users.getName())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8)) // 8 horas
                .signWith(getSigningKey())
                .compact();
    }

    // Extraer email del token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Validar si el token es válido
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extraer todos los claims
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Construir la clave de firma
    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}