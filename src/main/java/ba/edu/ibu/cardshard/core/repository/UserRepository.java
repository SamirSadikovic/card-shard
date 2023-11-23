package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.card.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ba.edu.ibu.cardshard.core.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> { }
