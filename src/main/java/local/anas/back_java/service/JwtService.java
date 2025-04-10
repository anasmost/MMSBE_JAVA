package local.anas.back_java.service;

import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  private SecretKey jwtSecret;

  @Value("${local.anas.back_java.jwt.issuer}")
  private String jwtIssuer;

  @Autowired
  void setJwtSecret(@Value("${local.anas.back_java.jwt.secret}") String jwtSecretString) {
    this.jwtSecret = Keys.hmacShaKeyFor(jwtSecretString.getBytes());
  }

  public String generateToken(String email) {
    return Jwts.builder().subject(email).issuer(jwtIssuer).issuedAt(new Date()).signWith(jwtSecret)
        .compact();
  }

  public Jwt<Header, Map<String, String>> parse(String token) {
    return (Jwt<Header, Map<String, String>>) Jwts.parser().verifyWith(jwtSecret).build()
        .parse(token);
  }

}
