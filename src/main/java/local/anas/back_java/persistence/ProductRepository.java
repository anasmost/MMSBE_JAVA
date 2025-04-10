package local.anas.back_java.persistence;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import local.anas.back_java.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

  @NonNull
  List<Product> findAll();

}
