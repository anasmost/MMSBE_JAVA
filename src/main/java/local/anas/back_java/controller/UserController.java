package local.anas.back_java.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import local.anas.back_java.model.User;
import local.anas.back_java.persistence.UserRepository;
import local.anas.back_java.service.JwtService;
import local.anas.back_java.validation.AccountGroup;
import local.anas.back_java.validation.TokenGroup;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
  final UserRepository userRepository;
  final PasswordEncoder passwordEncoder;
  final AuthenticationManager authenticationManager;
  final JwtService jwtService;

  @PostMapping("/account")
  @ResponseStatus(HttpStatus.CREATED)
  public void createAccount(@Validated(AccountGroup.class) @RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @PostMapping("/token")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> getToken(@Validated(TokenGroup.class) @RequestBody User user) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

    Jwt token = jwtService.createToken((User) authentication.getPrincipal());
    return Map.of("token", token.getTokenValue());
  }
}
