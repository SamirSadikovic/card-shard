package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.repository.CardRepository;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final MongoTemplate mongoTemplate;

    public CardService(CardRepository cardRepository, MongoTemplate mongoTemplate) {
        this.cardRepository = cardRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<CardDTO> getCards() {
        List<Card> cards = cardRepository.findAll();

        return cards
                .stream()
                .map(CardDTO::new)
                .collect(toList());
    }

    public List<CardDTO> getCardsByIds(ArrayList<Integer> ids) {
        List<Card> cards = cardRepository.findAllById(ids);

        if (cards.isEmpty())
            throw new ResourceNotFoundException("None of the cards from the list exists.");

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

    public CardDTO getCardByCollectedCardId(int cardId, String setCode) {
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isEmpty())
            throw new ResourceNotFoundException("The card with the given ID does not exist.");

        //remove all sets except the requested set
        card.get().getCardSets().removeIf(s -> (!s.getSetCode().equals(setCode)));
        if (card.get().getCardSets().isEmpty())
            throw new ResourceNotFoundException("The card did not get printed in the selected set.");

        return new CardDTO(card.get());
    }

    public Page<Card> filterCards(Query query, Pageable pageable) {
        List<Card> cardList = mongoTemplate.find(query, Card.class);

        return PageableExecutionUtils.getPage(
                cardList,
                pageable,
                () -> mongoTemplate.count(query.skip(-1).limit(-1), Card.class));
    }
}
