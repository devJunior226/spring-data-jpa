package dev.backend.demo.repository;

import dev.backend.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
   // ## get users which names containing keywords
   Iterable<User> findByNameContaining(String expression);

   // ## Requete equivalente, mais avec une syntaxe SQL
   @Query("FROM User u WHERE u.name LIKE %?1%")
   Iterable<User> getByExpression(String expression);


   @Query(value = "SELECT * FROM user u WHERE u.name LIKE %?1%",
       nativeQuery = true
   )
   Iterable<User> getByExpressionUsingNativeQuery(String expression);
}
