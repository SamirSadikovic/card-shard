package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.card.Card;
import ba.edu.ibu.cardshard.core.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(int id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty())
            throw new ResourceNotFoundException("The card with the given ID does not exist.");
        return card.get();
    }
}
