package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Deck;
import ba.edu.ibu.cardshard.core.repository.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getDecks() {
        return deckRepository.findAll();
    }

    public Deck getDeckById(String id) {
        Optional<Deck> deck = deckRepository.findById(id);
        if (deck.isEmpty())
            throw new ResourceNotFoundException("The deck with the given ID does not exist.");
        return deck.get();
    }
}
