package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.repository.CardRepository;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardDTO> getCards() {
        List<Card> cards = cardRepository.findAll();

        return cards
                .stream()
                .map(CardDTO::new)
                .collect(toList());
    }

    public CardDTO getCardById(int id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty())
            throw new ResourceNotFoundException("The card with the given ID does not exist.");
        return new CardDTO(card.get());
    }

    public List<CardDTO> filterByText(String textPattern) {
        List<Card> cards = cardRepository.findByNameOrDescLike(textPattern);
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("No cards match the criteria.");
        }
        return cards
                .stream()
                .map(CardDTO::new)
                .collect(toList());
    }
}
