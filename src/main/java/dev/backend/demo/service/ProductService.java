package dev.backend.demo.service;

import com.github.javafaker.Faker;
import dev.backend.demo.entity.Product;
import dev.backend.demo.entity.Sentiment;
import dev.backend.demo.entity.User;
import dev.backend.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductService {
   private ProductRepository productRepository;

   // * ### Create products, users and sentiments when we start the projetc
   /**
    * Generer un nombre aleatoire entre 30 et 100 produits avec IntStream et range
    * Pour constituer un ensemble de produits grace à collect(Collectors.toList())
    * On aura donc une liste de produits aleatoires sur lesquels on peut travailler
    * On mappe vers un new produit avec mapToObj
    */
   public void initializeProducts() {
      Faker faker = new Faker();

      final List<Product> products = IntStream.range(30, 100).mapToObj(index -> {
          // Create at the same time User and his opinions
          User user = User.builder()
              .name(faker.name().fullName()).build();

          // for opinions
          final List<Sentiment> sentiments = IntStream.range(2, 5).mapToObj(sentIndex ->
              Sentiment
                  .builder()
                  .user(user)
                     .creation(Instant.now())
                  .text(faker.lorem().sentence())
                  .build()
          ).collect(Collectors.toList());


          // Generation de produits aleatoires
          /**
           * On genere maintenant a la fois les produits
           * Les sentiments
           */
          return Product.builder()
              .sentiments(sentiments)
              .name("Product" + index)
              .price(index * 100)
              .build();
          }

      ).collect(Collectors.toList());

       // Generation de produits aleatoires
//       Product.builder()
//           .name("Product" + index)
//           .price(index + 100)
//           .build()
//      ).collect(Collectors.toList());

//            User user = User.builder()
//                    .name(faker.name().fullName())
//                    .build();
//
//            final List<Sentiment> sentiments = IntStream.range(5, 10).mapToObj(i ->
//                    Sentiment
//                            .builder()
//                            .user(user)
//                            .text(faker.lorem().sentence())
//                            .build()
//            ).collect(Collectors.toList());
//
//            return Product.builder()
//                    .sentiments(sentiments)
//                    .name("Product " + index)
//                    .price(index * 100)
//                    .build();
//        }).collect(Collectors.toList());

      // Creation des produits
      this.productRepository.saveAll(products);
   }

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
   public Iterable<Product> listProducts(String name, String sentiment) {
      if (Strings.isNotEmpty(name)) {
         return this.productRepository.findByNameContainingOrderByPrice(name);
      }if (Strings.isNotEmpty(sentiment)) {
         return this.productRepository.findBySentimentsTextContaining(sentiment);
      }
      return this.productRepository.findAll();
   }
}





