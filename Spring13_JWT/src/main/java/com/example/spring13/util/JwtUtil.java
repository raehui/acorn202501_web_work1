package com.example.spring13.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>(extraClaims);
        return createToken(claims, username);
    }
    // 긴 토큰 정보를 얻어오고
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // 추가 정보
                .setSubject(subject) //주요 정보(userName)
                .setIssuer("your-issuer") // 추가된 issuer(발급한 서비스명)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //파기되는 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //HS256 알고리즘으로 서명
                .compact();
    }
    // token 검증하는 메소드
    // 긴 토큰을 검증하는 방법
    public Boolean validateToken(String token) {
    	//token 으로 부토 userName 을 얻어낼 수 있다.
        final String username = extractUsername(token);
        //token 에 담긴 추가 정보를 얻어낼 수 있다. (role, issuer, email 등등)
        final Claims claims = extractAllClaims(token);
        // 웹서비스에서 발급받은 토큰을 앱에서 발급하지 못하게
        // 즉 교차검증
        return ( !isTokenExpired(token) &&
                "your-issuer".equals(claims.getIssuer())); // 추가 검증
    }
}
