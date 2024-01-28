package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.service.CardService;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    @RequestMapping(method = RequestMethod.GET, path = "/ids")
    public ResponseEntity<List<CardDTO>> getCardsById(@RequestParam ArrayList<Integer> ids) {
        return ResponseEntity.ok(cardService.getCardsByIds(ids));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable int id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/card/{cardId}/set/{setCode}")
    public ResponseEntity<CardDTO> getCardByIdAndSetCode(@PathVariable int cardId, @PathVariable String setCode) {
        return ResponseEntity.ok(cardService.getCardByCollectedCardId(cardId, setCode));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filter/{pageNumber}/{cardsPerPage}")
    public ResponseEntity<CardFilterResponse> filterCards(@RequestParam String text,
                                                     @RequestParam String type,
                                                     @RequestParam String race,
                                                     @RequestParam String monsterType,
                                                     @RequestParam String monsterFrame,
                                                     @RequestParam String attribute,
                                                     @RequestParam int levelRankLinkVal,
                                                     @RequestParam int scale,
                                                     @RequestParam int atk,
                                                     @RequestParam int def,
                                                     @RequestParam ArrayList<String> linkMarkers,
                                                     @PathVariable int pageNumber,
                                                     @PathVariable int cardsPerPage) {
        Query query = new Query();

        if (!text.equals("DEFAULT"))
            query.addCriteria(Criteria.where(null).orOperator(Criteria.where("name").regex(".*("+text+").*","i"), Criteria.where("desc").regex(".*("+text+").*", "i")));
        if (!type.equals("DEFAULT")) {
            //if type is Monster, check for other monster-specific query values and use regex to find them
            if (type.equals("Monster")) {
                ArrayList<String> monsterTypes = new ArrayList<>();
                monsterTypes.add(type);
                if (!monsterType.equals("DEFAULT"))
                    monsterTypes.add(monsterType);
                if (!monsterFrame.equals("DEFAULT"))
                    monsterTypes.add(monsterFrame);

                String monsterTypeRegex = "^";

                for (String mt : monsterTypes)
                    monsterTypeRegex += "(?=.*\\b"+mt+"\\b)";

                monsterTypeRegex += ".*$";
                query.addCriteria(Criteria.where("type").regex(monsterTypeRegex));
            } else {
                query.addCriteria(Criteria.where("type").is(type + " Card"));
            }
        }
        if (!race.equals("DEFAULT"))
            query.addCriteria(Criteria.where("race").is(race));
        if (!attribute.equals("DEFAULT"))
            query.addCriteria(Criteria.where("attribute").is(attribute));
        if (levelRankLinkVal != -1)
            query.addCriteria(Criteria.where(null).orOperator(Criteria.where("level").is(levelRankLinkVal), Criteria.where("linkVal").is(levelRankLinkVal)));
        if (scale != -1)
            query.addCriteria(Criteria.where("scale").is(scale));
        if (atk != -1)
            query.addCriteria(Criteria.where("atk").is(atk));
        if (def != -1)
            query.addCriteria(Criteria.where("def").is(def));
        if (!linkMarkers.get(0).equals("DEFAULT"))
            query.addCriteria(Criteria.where("linkMarkers").all(linkMarkers));

        final PageRequest pageable = PageRequest.of(pageNumber, cardsPerPage);

        query.with(pageable);
        query.with(Sort.by(Sort.Direction.ASC, "name"));
        
        Page<Card> cardPage = cardService.filterCards(query, pageable);

        CardFilterResponse response = new CardFilterResponse();
        response.setCards(cardPage.getContent());
        response.setCurrentPage(cardPage.getNumber());
        response.setTotalPages(cardPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    private static class CardFilterResponse {
        private List<Card> cards;
        private int totalPages;
        private int currentPage;

        public CardFilterResponse() {}

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}