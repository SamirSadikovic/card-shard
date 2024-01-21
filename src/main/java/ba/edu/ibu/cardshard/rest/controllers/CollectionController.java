package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.service.CollectionService;
import ba.edu.ibu.cardshard.rest.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/collections")
@SecurityRequirement(name = "JWT Security")
public class CollectionController {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<CollectionDTO>> getCollections() {
        return ResponseEntity.ok(collectionService.getCollections());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CollectionDTO> getCollectionById(@PathVariable String id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/collection")
    public ResponseEntity<CollectionDTO> getCollectionByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(collectionService.getCollectionByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<CollectionDTO> create(@RequestBody CollectionRequestDTO collection) {
        return ResponseEntity.ok(collectionService.createCollection(collection));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable String id, @RequestBody CollectionRequestDTO collection) {
        return ResponseEntity.ok(collectionService.updateCollection(id, collection));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/cards/add")
    public ResponseEntity<CollectionDTO> addCard(@PathVariable String id, @RequestBody CollectedCardRequestDTO collectedCard) {
        return ResponseEntity.ok(collectionService.addCard(id, collectedCard));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/cards/edit")
    public ResponseEntity<CollectionDTO> editCard(@PathVariable String id, @RequestBody CollectedCardRequestDTO collectedCard) {
        return ResponseEntity.ok(collectionService.editCard(id, collectedCard));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/cards/remove")
    public ResponseEntity<CollectionDTO> removeCard(@PathVariable String id, @RequestBody CollectedCardRequestDTO collectedCard) {
        return ResponseEntity.ok(collectionService.removeCard(id, collectedCard));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable String id) {
        collectionService.deleteCollection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}/trades")
    public ResponseEntity<List<CollectedCardDTO>> getTradesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(collectionService.getTradesByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/tags")
    public ResponseEntity<HashSet<String>> getTagsByCollectionId(@PathVariable String id) {
        return ResponseEntity.ok(collectionService.getTagsByCollectionId(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/tag/{tags}")
    public ResponseEntity<List<CollectedCardDTO>> getTaggedCards(@PathVariable String id, @PathVariable ArrayList<String> tags) {
        return ResponseEntity.ok(collectionService.getTaggedCards(id, tags));
    }
}