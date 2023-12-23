package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.CollectedCard;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.repository.CollectionRepository;
import ba.edu.ibu.cardshard.rest.dto.CollectedCardDTO;
import ba.edu.ibu.cardshard.rest.dto.CollectionDTO;
import ba.edu.ibu.cardshard.rest.dto.CollectionRequestDTO;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionDTO> getCollections() {
        List<Collection> collections = collectionRepository.findAll();

        return collections
                .stream()
                .map(CollectionDTO::new)
                .collect(toList());
    }

    public CollectionDTO getCollectionById(String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        return new CollectionDTO(collection.get());
    }

    public CollectionDTO getCollectionByUserId(String userId) {
        Optional<Collection> collection = collectionRepository.findByUserId(userId);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given user ID does not exist.");
        return new CollectionDTO(collection.get());
    }

    public CollectionDTO createCollection(CollectionRequestDTO payload) {
        Collection collection = collectionRepository.save(payload.toEntity());
        return new CollectionDTO(collection);
    }

    public CollectionDTO updateCollection(String id, CollectionRequestDTO payload) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        }
        Collection updatedCollection = payload.toEntity();
        updatedCollection.setId(collection.get().getId());
        updatedCollection = collectionRepository.save(updatedCollection);
        return new CollectionDTO(updatedCollection);
    }

    public void deleteCollection(String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        collection.ifPresent(collectionRepository::delete);
    }

    public List<CollectedCardDTO> getTradesByUserId(String userId) {
        Optional<Collection> collection = collectionRepository.findByUserId(userId);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given user ID does not exist.");

        List<CollectedCard> cardsForTrade = new ArrayList<>();

        for (CollectedCard collectedCard : collection.get().getCards())
            if (collectedCard.getSellTrade())
                cardsForTrade.add(collectedCard);

        return cardsForTrade
                .stream()
                .map(CollectedCardDTO::new)
                .collect(toList());

    }

    public HashSet<String> getTagsByCollectionId(String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");

        HashSet<String> tags = new HashSet<>();

        for (CollectedCard collectedCard : collection.get().getCards())
            tags.addAll(collectedCard.getTags());

        if (tags.isEmpty())
            throw new ResourceNotFoundException("There are no tags in this collection.");

        return tags;
    }

    public List<CollectedCardDTO> getTaggedCards(String id, String tag) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");

        List<CollectedCard> cardsInTag = new ArrayList<>();

        for (CollectedCard collectedCard : collection.get().getCards())
            if (collectedCard.getTags().contains(tag))
                cardsInTag.add(collectedCard);

        if (cardsInTag.isEmpty())
            throw new ResourceNotFoundException("There are no cards with the given tag in this collection.");

        return cardsInTag
                .stream()
                .map(CollectedCardDTO::new)
                .collect(toList());

    }
}
