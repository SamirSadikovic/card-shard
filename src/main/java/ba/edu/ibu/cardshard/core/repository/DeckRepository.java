package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Deck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepository extends MongoRepository<Deck, String> {
    Optional<Deck> findByUserId(String userId);
    @Query(value="{'$or':[{'main': ?0}, {'side': ?0}, {'extra': ?0}]}")
    List<Deck> findByCard(int cardId);
}
