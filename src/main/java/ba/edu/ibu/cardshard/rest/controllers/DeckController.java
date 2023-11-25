package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.service.DeckService;
import ba.edu.ibu.cardshard.rest.dto.DeckDTO;
import ba.edu.ibu.cardshard.rest.dto.DeckRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/decks")
@SecurityRequirement(name = "JWT Security")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<DeckDTO>> getDecks() {
        return ResponseEntity.ok(deckService.getDecks());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<DeckDTO> getDeckById(@PathVariable String id) {
        return ResponseEntity.ok(deckService.getDeckById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<DeckDTO> register(@RequestBody DeckRequestDTO deck) {
        return ResponseEntity.ok(deckService.addDeck(deck));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<DeckDTO> updateDeck(@PathVariable String id, @RequestBody DeckRequestDTO deck) {
        return ResponseEntity.ok(deckService.updateDeck(id, deck));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable String id) {
        deckService.deleteDeck(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
