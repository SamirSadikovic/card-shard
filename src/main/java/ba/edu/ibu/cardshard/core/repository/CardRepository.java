package ba.edu.ibu.cardshard.core.repository;

import org.springframework.stereotype.Repository;
import ba.edu.ibu.cardshard.core.model.card.Card;

import java.util.Arrays;
import java.util.List;

@Repository
public class CardRepository {

    private List<String> cards;

    public CardRepository(){
        this.cards = Arrays.asList("Card1", "Card2", "Card3");
    }

    public List<String> findAll() {
        return this.cards;
    }

    private String findById(int id){
        return this.cards.get(id);
    }
}
