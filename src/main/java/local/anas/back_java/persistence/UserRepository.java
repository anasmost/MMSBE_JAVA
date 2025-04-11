package local.anas.back_java.persistence;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import local.anas.back_java.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByEmail(String email);

}
