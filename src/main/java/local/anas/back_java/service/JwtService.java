package local.anas.back_java.service;

import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import local.anas.back_java.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
  private final JwtEncoder jwtEncoder;

  @Value("#{T(java.time.Duration).parse('${local.anas.back_java.jwt.duration}')}")
  private Duration jwtDuration;

  @Value("${local.anas.back_java.jwt.issuer}")
  private String jwtIssuer;

  public Jwt createToken(User user) {
    return jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),
        JwtClaimsSet.builder().subject(user.getEmail())
            .claim("roles",
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
            .issuer(jwtIssuer).expiresAt(Instant.now().plus(jwtDuration)).build()));
  }

}
