package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.Deck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends MongoRepository<Deck, String> { }
