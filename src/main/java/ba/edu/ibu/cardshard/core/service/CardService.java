package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.model.card.Card;
import ba.edu.ibu.cardshard.core.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findById(@PathVariable int id) {
        return cardRepository.findById(id);
    }
}
