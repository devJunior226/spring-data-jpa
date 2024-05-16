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
      this.productRepository.saveAll(products);
   }

   public Iterable<Product> listProducts(String name, String sentiment) {
      if (Strings.isNotEmpty(name)) {
         return this.productRepository.findByNameContainingOrderByPrice(name);
      }if (Strings.isNotEmpty(sentiment)) {
         return this.productRepository.findBySentimentsTextContaining(sentiment);
      }
      return this.productRepository.findAll();
   }
}





