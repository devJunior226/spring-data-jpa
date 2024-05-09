package dev.backend.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;

    /**
     * Un produit a plusieurs sentiments: OneToMany
     * Quand c'est du OneToMany, on aura une collection de l'entité sentiment
     * dans l'entité product
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sentiment> sentiments;
}















