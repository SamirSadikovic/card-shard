package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.service.CardService;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    TODO:
     Add endpoints for filtering
 */

@RestController
@RequestMapping("api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<CardDTO>> getCards() {
        return ResponseEntity.ok(cardService.getCards());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable int id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }
}
