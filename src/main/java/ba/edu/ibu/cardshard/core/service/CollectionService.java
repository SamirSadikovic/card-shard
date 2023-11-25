package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.general.BadRequestException;
import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.repository.CollectionRepository;
import ba.edu.ibu.cardshard.rest.dto.CollectionDTO;
import ba.edu.ibu.cardshard.rest.dto.CollectionRequestDTO;
import ba.edu.ibu.cardshard.rest.dto.UserDTO;
import ba.edu.ibu.cardshard.rest.dto.UserRequestDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public CollectionDTO addCollection(CollectionRequestDTO payload) {
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
}
