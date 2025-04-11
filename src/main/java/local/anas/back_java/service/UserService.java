package local.anas.back_java.service;

import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import local.anas.back_java.model.Product;
import local.anas.back_java.model.User;
import local.anas.back_java.persistence.ProductRepository;
import local.anas.back_java.persistence.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  final UserRepository userRepository;
  final ProductRepository productRepository;

  public Set<Product> getCart(User userPrincipal) {
    return userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow().getCart();
  }

  public void addToCart(Product productDto, User userPrincipal) {
    final User user = userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow();
    final Product product = productRepository.findById(productDto.getId()).orElseThrow();

    user.getCart().add(product);

    userRepository.save(user);
  }

  public void deleteFromCart(Long productId, User userPrincipal) {
    final User user = userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow();

    user.getCart().removeIf(p -> p.getId().equals(productId));

    userRepository.save(user);
  }

  public Set<Product> getWishlist(User userPrincipal) {
    return userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow().getWishlist();
  }

  public void addToWishlist(Product productDto, User userPrincipal) {
    final User user = userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow();
    final Product product = productRepository.findById(productDto.getId()).orElseThrow();

    user.getWishlist().add(product);

    userRepository.save(user);
  }

  public void deleteFromWishlist(Long productId, User userPrincipal) {
    final User user = userRepository.findByEmail(userPrincipal.getEmail()).orElseThrow();

    user.getWishlist().removeIf(p -> p.getId().equals(productId));

    userRepository.save(user);
  }

}
