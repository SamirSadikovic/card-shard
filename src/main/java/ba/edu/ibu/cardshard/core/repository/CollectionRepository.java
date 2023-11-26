package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
    Optional<Collection> findByUserId(String userId);
}
