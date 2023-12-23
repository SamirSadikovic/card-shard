package ba.edu.ibu.cardshard.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ba.edu.ibu.cardshard.core.model.Card;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends MongoRepository<Card, Integer> {
    Optional<Card> findById(int id);

    @Query(value="{$or:[{name: { $regex: /.*(?0).*/, $options: 'i'}}," +
                       "{desc: { $regex: /.*(?0).*/, $options: 'i'}}]}")
    List<Card> findByNameOrDescLike(String textPattern);
}
