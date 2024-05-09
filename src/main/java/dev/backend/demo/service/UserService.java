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

   // ################# Get products list
   /**
    * On veut en meme temps permettre la recherche de produit par nom;
    * On verfie d'abord si le nom existe,
    * on retourne le produit qui porte ce nom(le findByName)
    * Sinon, on retourne la liste des produits
    */

   /**
    *     public Iterable<ProductEntity> listProducts(String name) {
    *         if (Strings.isNotEmpty(name)) {
    *             return this.productRepository.findByName(name);
    *         }
    *         return this.productRepository.findAll();
    *     }
    *
    *     public Iterable<ProductEntity> listProducts(String name) {
    *         if (Strings.isNotEmpty(name)) {
    *             return this.productRepository.findByNameContaining(name);
    *         }
    *         return this.productRepository.findAll();
    *     }
    */

   /**
    * @param name
    * @return products where name contain the param mentioned into the url
    * et ordered by price height
    *
    * We also want to return all products according the text
    * key entered en param.
    * ie we add sentiment param
    */

   public Iterable<User> listUsers(String name) {
      if (Strings.isNotEmpty(name)) {
         return this.userRepository.getByExpressionUsingNativeQuery(name);
         //return this.userRepository.getByExpression(name);
         //return this.userRepository.findByNameContaining(name);
      }
         return this.userRepository.findAll();
   }
}





