package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
    Optional<Collection> findByUserId(String userId);

    @Query(value="{id: '?0'}", fields="{'tags': 1}")
    Optional<HashSet<Tag>> getTagsById(String id);

    @Query(value="{userId: '?0'}", fields="{'tags': 1}")
    Optional<HashSet<Tag>> getTagsByUserId(String userId);

    Optional<Integer> getCollectionSizeById(String id);

    @Aggregation(pipeline = """
        { $unwind: '$cards' }, { $group: { _id: '$cards.id', count: { $sum: 1 } } } }
    """)
    Optional<Map<String, Integer>> getCardQuantities();
}
