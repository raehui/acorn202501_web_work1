package com.example.spring15.util;

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

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) //token 발급시 서명했던 키값(csutom.properties)도 일치하는지 확인도 된다. 서명했던 키값이 일치하지 않으면 token 생성 안됨
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
    // 토큰의 키값, issuer, role 검증
    public boolean validateToken(String token) {
    	//token 에 담긴 추가 정보를 얻어낼 수 잇다.(role, issuer 등등)
    	Claims claims = extractAllClaims(token);
    	//토큰 유효시간이 남아 잇는지와 issuer 정보도 일치하는지 확인해서
    	
    	boolean isValid = !isTokenExpired(token) && "your-issuer".equals(claims.getIssuer());
    	//유효성 여부를 리턴한다.
    	return isValid;
    }
}
