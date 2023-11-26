package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.service.CollectionService;
import ba.edu.ibu.cardshard.rest.dto.CollectionDTO;
import ba.edu.ibu.cardshard.rest.dto.CollectionRequestDTO;
import ba.edu.ibu.cardshard.rest.dto.UserDTO;
import ba.edu.ibu.cardshard.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/*
    TODO:
     Implement collection privacy
     Add trade binder functionality
     Revise endpoints that return tags and counts
 */

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

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public ResponseEntity<CollectionDTO> getCollectionByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(collectionService.getCollectionByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<CollectionDTO> register(@RequestBody CollectionRequestDTO collection) {
        return ResponseEntity.ok(collectionService.addCollection(collection));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable String id, @RequestBody CollectionRequestDTO collection) {
        return ResponseEntity.ok(collectionService.updateCollection(id, collection));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable String id) {
        collectionService.deleteCollection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}