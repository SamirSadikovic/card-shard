package ba.edu.ibu.cardshard.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ba.edu.ibu.cardshard.core.model.card.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, Integer> { }
