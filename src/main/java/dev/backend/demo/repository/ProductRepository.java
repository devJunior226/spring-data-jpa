package dev.backend.demo.repository;

import dev.backend.demo.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Puisque c'est le repository qui assure la liaison a la bd,
 * Il prend en params l'objet a transmettre et le type de son id
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    /**
     * Rechercher un produit selon id, l'equivalent de:
     * SELECT * FROM product WHERE name = param;
     */
    Iterable<Product> findByName(String name);

    /**
     * Retourner un produit dont le nom contient un param donné
     */
    Iterable<Product> findByNameContaining(String name);

    /**
     *
     * @param name
     * @return according the param given by the user
     */
    Iterable<Product> findByNameContainingOrderByPrice(String name);

    /**
     * Search by an interval of price
     */
    Iterable<Product> findByPriceAfter(int price);

    // ## Return the list of products and the opinions

    /**
     * L'equivalent SQL de:
     * Select *
     * FROM product, sentiment
     * Join product, sentiment
     * Et ira chercher le produit qui contient le sentiment contenant
     * le texte passé en param
     */
    List<Product> findBySentimentsTextContaining(String text);
}







