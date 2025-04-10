package local.anas.back_java.controller;

import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import local.anas.back_java.model.Product;
import local.anas.back_java.model.User;
import local.anas.back_java.service.UserService;
import lombok.RequiredArgsConstructor;


@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
  final UserService userService;

  @GetMapping
  public Set<Product> get(@AuthenticationPrincipal User userPrincipal) {
    return userService.getCart(userPrincipal);
  }

  @PostMapping
  public void addToCart(@RequestBody Product product, @AuthenticationPrincipal User userPrincipal) {
    userService.addToCart(product, userPrincipal);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteFromCart(@PathVariable("id") Long productId,
      @AuthenticationPrincipal User userPrincipal) {
    userService.deleteFromCart(productId, userPrincipal);
  }

}
