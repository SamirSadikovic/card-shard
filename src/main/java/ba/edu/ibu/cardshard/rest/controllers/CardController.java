package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.service.CardService;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<List<CardDTO>> filterCards(@RequestParam String text,
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
                                                     @RequestParam int pageNumber) {
        Query queryNew = new Query();

        if (!text.equals("DEFAULT"))
            queryNew.addCriteria(Criteria.where(null).orOperator(Criteria.where("name").regex(".*("+text+").*","i"), Criteria.where("desc").regex(".*("+text+").*", "i")));
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
                queryNew.addCriteria(Criteria.where("type").regex(monsterTypeRegex));
            } else {
                queryNew.addCriteria(Criteria.where("type").is(type + " Card"));
            }
        }
        if (!race.equals("DEFAULT"))
            queryNew.addCriteria(Criteria.where("race").is(race));
        if (!attribute.equals("DEFAULT"))
            queryNew.addCriteria(Criteria.where("attribute").is(attribute));
        if (levelRankLinkVal != -1)
            queryNew.addCriteria(Criteria.where(null).orOperator(Criteria.where("level").is(levelRankLinkVal), Criteria.where("linkVal").is(levelRankLinkVal)));
        if (scale != -1)
            queryNew.addCriteria(Criteria.where("scale").is(scale));
        if (atk != -1)
            queryNew.addCriteria(Criteria.where("atk").is(atk));
        if (def != -1)
            queryNew.addCriteria(Criteria.where("def").is(def));
        if (!linkMarkers.get(0).equals("DEFAULT"))
            queryNew.addCriteria(Criteria.where("linkMarkers").all(linkMarkers));

        final PageRequest pageableRequest = PageRequest.of(pageNumber, 10);
        queryNew.with(pageableRequest);
        queryNew.with(Sort.by(Sort.Direction.ASC, "name"));

        System.out.println(queryNew.getQueryObject());

        return ResponseEntity.ok(cardService.filterCards(queryNew));
    }
}