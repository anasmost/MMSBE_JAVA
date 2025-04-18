package local.anas.back_java.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.JsonMappingException;
import local.anas.back_java.annotation.security.AuthorizeAdminOnly;
import local.anas.back_java.mapper.ProductMapper;
import local.anas.back_java.model.Product;
import local.anas.back_java.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @GetMapping
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  public Product getOne(@PathVariable long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @AuthorizeAdminOnly
  @PostMapping
  public Product createOne(@RequestBody Product product) {
    return productRepository.save(product);
  }

  @AuthorizeAdminOnly
  @PatchMapping("/{id}")
  @Transactional
  public Product updateOne(@PathVariable long id, @RequestBody Product productDto)
      throws JsonMappingException {
    final Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    productMapper.updateProductFromDto(product, productDto);

    return productRepository.save(product);
  }

  @AuthorizeAdminOnly
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Transactional
  public void deleteOne(@PathVariable long id) {
    if (productRepository.findById(id).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    productRepository.deleteById(id);
  }

}

