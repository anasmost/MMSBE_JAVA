package local.anas.back_java.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  private SecretKey jwtSecret;
  private Duration jwtDuration;

  @Value("${local.anas.back_java.jwt.issuer}")
  private String jwtIssuer;

  @Autowired
  void setJwtConfig(@Value("${local.anas.back_java.jwt.secret}") String jwtSecretString,
      @Value("${local.anas.back_java.jwt.duration}") String jwtDuration) {
    this.jwtSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretString));
    this.jwtDuration = Duration.parse(jwtDuration);
  }

  public String generateToken(String email) {
    return Jwts.builder().subject(email).issuer(jwtIssuer).issuedAt(new Date())
        .expiration(Date.from(Instant.now().plus(jwtDuration))).signWith(jwtSecret).compact();
  }

  public Jwt<Header, Map<String, String>> parse(String token) {
    return (Jwt<Header, Map<String, String>>) Jwts.parser().verifyWith(jwtSecret).build()
        .parse(token);
  }

}
