package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.CollectedCard;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectedCardRepository extends MongoRepository<CollectedCard, CollectedCard.CollectedCardId> {

    @Aggregation(pipeline = """
            { $match: { 'userId': '?0', 'cards.sellTrade': true } },
            { $unwind: { path: '$cards' } }
    """)
    List<CollectedCard> findTradesByUserId(String userId);
}