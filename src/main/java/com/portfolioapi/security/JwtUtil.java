package com.portfolioapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "2752384ea86e567bd49cedb39faa624e0b852c0a24190ec76fda3e7f05b50b6388c4451fc9be8ba3009ae9df292b177b0e09b7f607afad23a32fe6656c6521913c8dd0bd45a18d3f778d1a42bb2e66737fdbd062295bef5811b36e59b8903c7a309f4c87bedf3adac4d9fa53715afd9c5a0075a1313b9988c0fdca5eabf36d95a8f20845d071d3829fb0413fd7f791bb316d50a0531a1bb7a3adf66355dc032017c6b54db897164810439a9c885d6829eb20b3d60be9c7c90881effe42236fc6542e46f4d21a1e91f4dd881b184613741a65197bbd6606bfbfe22070601533280c7703754b977b0e454d2a4948f9e29b61292ebb1dfa64f741363d3e50022a7a";

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
