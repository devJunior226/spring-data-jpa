package dev.backend.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
//@Table(name = "sentiment")
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sentiment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private Instant creation; // Date a laquelle le sentiment a ete cree

    /**
     * ### Liaison entre les entités
     * ### Un utilisateur peut deposer plusieurs sentiments: OneToMany
     * Quand c'est du OneToMany, on aura une collection de l'entité
     * vers laquelle on etablie le OneToMany dans la table actuelle
     * D'un autre coté, Plusieurs sentiments sont donc déposés par un seul user: ManyToOne
     * Et ce ManyToOne, c'est vers la table User
     * Dans ce cas, une instance de User doit se retrouver dans l'entité sentiment
     *
     * On n'oublie pas de mentionner l'attrbut qui constitue la liaison
     * sinon Spring le ferait pour nous
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
















