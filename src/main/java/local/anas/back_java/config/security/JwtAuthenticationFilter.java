package local.anas.back_java.config.security;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import local.anas.back_java.model.User;
import local.anas.back_java.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException, java.io.IOException {
    final String authHeader = request.getHeader("Authorization");

    if (!Strings.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = authHeader.substring(7);

    if (SecurityContextHolder.getContext().getAuthentication() == null) {

      val jwtObject = jwtService.parse(token);
      final User user = User.builder().email(jwtObject.getPayload().get("sub").toString()).build();

      SecurityContext context = SecurityContextHolder.createEmptyContext();
      PreAuthenticatedAuthenticationToken authToken =
          new PreAuthenticatedAuthenticationToken(user, token, user.getAuthorities());
      context.setAuthentication(authToken);
      SecurityContextHolder.setContext(context);
    }

    filterChain.doFilter(request, response);
  }
}
