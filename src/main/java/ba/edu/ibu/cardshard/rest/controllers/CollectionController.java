package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.service.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/collections")
public class CollectionController {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<Collection>> getCollections() {
        return ResponseEntity.ok(collectionService.getCollections());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable String id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public ResponseEntity<Collection> getCollectionByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(collectionService.getCollectionByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tags/{id}")
    public ResponseEntity<HashSet<Tag>> getTagsById(@PathVariable String id) {
        return ResponseEntity.ok(collectionService.getTagsById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tags/user/{userId}")
    public ResponseEntity<HashSet<Tag>> getTagsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(collectionService.getTagsByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/collection-size/{id}")
    public ResponseEntity<Integer> getCollectionSize(@PathVariable String id) {
        return ResponseEntity.ok(collectionService.getCollectionSize(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/card-quantities")
    public ResponseEntity<Map<String, Integer>> getCardQuantities() {
        return ResponseEntity.ok(collectionService.getCardQuantities());
    }
}