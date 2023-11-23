package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.card.Card;
import ba.edu.ibu.cardshard.core.service.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> findAll() {
        return this.cardService.findAll();
    }

    @GetMapping("/{id}")
    public Card findById(@PathVariable int id) {
        return this.cardService.findById(id);
    }
}
