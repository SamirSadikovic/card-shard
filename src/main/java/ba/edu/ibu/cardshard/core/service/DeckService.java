package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Deck;
import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.repository.DeckRepository;
import ba.edu.ibu.cardshard.rest.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<DeckDTO> getDecks() {
        List<Deck> decks = deckRepository.findAll();

        return decks
                .stream()
                .map(DeckDTO::new)
                .collect(toList());
    }

    public DeckDTO getDeckById(String id) {
        Optional<Deck> deck = deckRepository.findById(id);
        if (deck.isEmpty()) {
            throw new ResourceNotFoundException("The deck with the given ID does not exist.");
        }
        return new DeckDTO(deck.get());
    }

    public DeckDTO getDeckByUserId(String userId) {
        Optional<Deck> deck = deckRepository.findByUserId(userId);
        if (deck.isEmpty())
            throw new ResourceNotFoundException("The deck with the given user ID does not exist.");
        return new DeckDTO(deck.get());
    }

    public List<DeckDTO> getDeckByCardId(int cardId) {
        List<Deck> decks = deckRepository.findByCard(cardId);
        if (decks.isEmpty())
            throw new ResourceNotFoundException("There are no decks containing that card.");

        return decks
                .stream()
                .map(DeckDTO::new)
                .collect(toList());
    }

    public DeckDTO addDeck(DeckRequestDTO payload) {
        Deck deck = deckRepository.save(payload.toEntity());
        return new DeckDTO(deck);
    }

    public DeckDTO updateDeck(String id, DeckRequestDTO payload) {
        Optional<Deck> deck = deckRepository.findById(id);
        if (deck.isEmpty()) {
            throw new ResourceNotFoundException("The deck with the given ID does not exist.");
        }
        Deck updatedDeck = payload.toEntity();
        updatedDeck.setId(deck.get().getId());
        updatedDeck = deckRepository.save(updatedDeck);
        return new DeckDTO(updatedDeck);
    }

    public void deleteDeck(String id) {
        Optional<Deck> deck = deckRepository.findById(id);
        deck.ifPresent(deckRepository::delete);
    }
}
