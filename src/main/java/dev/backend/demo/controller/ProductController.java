package dev.backend.demo.controller;

import dev.backend.demo.entity.Product;
import dev.backend.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(path = "products", produces = APPLICATION_JSON_VALUE)
public class ProductController {
    private ProductService productService;

    /**
     * Retourner la liste des produits
     * Pour permettre la recherche ici, on ajoute le @RequestParam
     * et on mentionne qu'il est optionnel(pas obligé de mentionner le nom a search)
     * Puis on mentionne l'attribut a chercher
     */
    @GetMapping
    public Iterable<Product> getAllProducts(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String sentiment
    ) {
        return this.productService.listProducts(name, sentiment);
    }
}
