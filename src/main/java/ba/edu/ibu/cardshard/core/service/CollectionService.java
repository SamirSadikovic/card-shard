package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.CollectedCard;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;
import ba.edu.ibu.cardshard.core.repository.CollectionRepository;
import ba.edu.ibu.cardshard.rest.dto.CollectedCardDTO;
import ba.edu.ibu.cardshard.rest.dto.CollectedCardRequestDTO;
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

    public CollectionDTO addCard(String id, CollectedCardRequestDTO payload) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        }
        CollectedCard collectedCard = payload.toEntity();
        ArrayList<CollectedCard> cardsInCollection = collection.get().getCards();

        // if card is already present just increase the quantity, otherwise add it as new card
        if (cardsInCollection.contains(collectedCard)){
            int index = cardsInCollection.indexOf(collectedCard);
            CollectedCard card = cardsInCollection.get(index);
            collection.get().getCards().get(index).setQuantity(card.getQuantity() + collectedCard.getQuantity());
        } else {
            collection.get().addCard(collectedCard);
        }

        Collection updatedCollection = new Collection(collection.get().getId(), collection.get().getUserId(), collection.get().getCards(), collection.get().getVisibilityType());
        updatedCollection = collectionRepository.save(updatedCollection);
        return new CollectionDTO(updatedCollection);
    }

    public CollectionDTO editCard(String id, CollectedCardRequestDTO payload) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        }
        CollectedCard updatedCard = payload.toEntity();
        ArrayList<CollectedCard> cardsInCollection = collection.get().getCards();

        if (!cardsInCollection.contains(updatedCard))
            throw new ResourceNotFoundException("This card does not exist in the collection.");

        int index = cardsInCollection.indexOf(updatedCard);
        collection.get().getCards().get(index).updateCard(updatedCard);

        if (collection.get().getCards().get(index).getQuantity() < 1)
            collection.get().getCards().remove(index);

        Collection updatedCollection = new Collection(collection.get().getId(), collection.get().getUserId(), collection.get().getCards(), collection.get().getVisibilityType());
        updatedCollection = collectionRepository.save(updatedCollection);
        return new CollectionDTO(updatedCollection);
    }

    public CollectionDTO removeCard(String id, CollectedCardRequestDTO payload) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");
        }
        CollectedCard collectedCard = payload.toEntity();
        ArrayList<CollectedCard> cardsInCollection = collection.get().getCards();

        if (cardsInCollection.contains(collectedCard)){
            collection.get().getCards().remove(collectedCard);
        } else {
            throw new ResourceNotFoundException("The card is not in this collection");
        }

        Collection updatedCollection = new Collection(collection.get().getId(), collection.get().getUserId(), collection.get().getCards(), collection.get().getVisibilityType());
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

        return tags;
    }

    public List<CollectedCardDTO> getTaggedCards(String id, ArrayList<String> tags) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty())
            throw new ResourceNotFoundException("The collection with the given ID does not exist.");

        List<CollectedCard> cardsInTag = new ArrayList<>();

        for (CollectedCard collectedCard : collection.get().getCards())
            if (collectedCard.getTags().containsAll(tags))
                cardsInTag.add(collectedCard);

        if (cardsInTag.isEmpty())
            throw new ResourceNotFoundException("There are no cards with the given tags in this collection.");

        return cardsInTag
                .stream()
                .map(CollectedCardDTO::new)
                .collect(toList());

    }
}
