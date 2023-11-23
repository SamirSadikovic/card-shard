package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.general.BadRequestException;
import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getCollections() {
        return collectionRepository.findAll();
    }

    public Collection getCollectionById(String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        return collection.get();
    }

    public Collection getCollectionByUserId(String userId) {
        Optional<Collection> collection = collectionRepository.findByUserId(userId);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given user ID does not exist.");
        return collection.get();
    }

    public HashSet<Tag> getTagsById(String id) {
        Optional<HashSet<Tag>> tags = collectionRepository.getTagsById(id);
        if (tags.isEmpty())
            throw new ResourceNotFoundException("Tags with the given collection ID do not exist.");
        return tags.get();
    }

    public HashSet<Tag> getTagsByUserId(String id) {
        Optional<HashSet<Tag>> tags = collectionRepository.getTagsByUserId(id);
        if (tags.isEmpty())
            throw new ResourceNotFoundException("Tags with the given user ID do not exist.");
        return tags.get();
    }

    public int getCollectionSize(String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        return collection.get().getCollectionSize();
    }

    public Map<String, Integer> getCardQuantities() {
        Optional<Map<String, Integer>> cardQuantities = collectionRepository.getCardQuantities();
        if (cardQuantities.isEmpty())
            throw new BadRequestException("Error during counting.");
        return cardQuantities.get();
    }
}
