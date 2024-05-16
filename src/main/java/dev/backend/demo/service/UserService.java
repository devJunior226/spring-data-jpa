package dev.backend.demo.service;

import dev.backend.demo.entity.User;
import dev.backend.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {
   private UserRepository userRepository;


   public Iterable<User> listUsers(String name) {
      if (Strings.isNotEmpty(name)) {
         return this.userRepository.getByExpressionUsingNativeQuery(name);
      }
         return this.userRepository.findAll();
   }
}





