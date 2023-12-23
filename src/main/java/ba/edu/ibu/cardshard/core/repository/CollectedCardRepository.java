package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.CollectedCard;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectedCardRepository extends MongoRepository<CollectedCard, Integer> {

    @Aggregation(pipeline = """
            { $match: { 'userId': '?0', 'cards.sellTrade': true } },
            { $unwind: { path: '$cards' } },
            { $project: { _id: 0, tags: 0, userId: 0 } }
    """)
    List<CollectedCard> findTradesByUserId(String userId);
}
