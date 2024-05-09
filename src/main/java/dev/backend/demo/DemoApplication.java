package dev.backend.demo;

import dev.backend.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication

/**
 * A demarrage de l'appli, on veut generer un nombre de produits aleatoire
 * D'ou l'importation de l'interface CommandLineRunner, une cmd qui s'execute au depart
 */
public class DemoApplication implements CommandLineRunner {
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.productService.initializeProducts();
	}
}
