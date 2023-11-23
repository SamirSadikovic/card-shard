package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.card.SpellCard;
import org.springframework.stereotype.Repository;
import ba.edu.ibu.cardshard.core.model.card.Card;

import java.util.Arrays;
import java.util.List;

@Repository
public class CardRepository {

    private List<Card> cards;

    public CardRepository(){
        this.cards = Arrays.asList(
                new SpellCard(83764719, "Monster Reborn", "Spell Card", "Target 1 monster in either GY; Special Summon it.", "Normal", "TN19-EN011", "4.44"),
                new SpellCard(4206964, "Trap Hole", "Trap Card", "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.", "Normal", "YS15-ENY17", "4.1")
        );
    }

    public List<Card> findAll() {
        return this.cards;
    }

    public Card findById(int id){
        return cards.stream().filter(card -> card.getId() == id).findFirst().orElse(null);
    }
}
