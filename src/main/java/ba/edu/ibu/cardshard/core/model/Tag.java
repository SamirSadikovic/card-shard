package ba.edu.ibu.cardshard.core.model;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class Tag {
    private String name;
    private HashSet<String> cardIds;

    public Tag(String name, HashSet<String> cardIds) {
        this.name = name;
        this.cardIds = cardIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<String> getCardIds() {
        return cardIds;
    }

    public void setCardIds(HashSet<String> cardIds) {
        this.cardIds = cardIds;
    }

    public void addCard(String cardId) {
        this.cardIds.add(cardId);
    }

    public void removeCard(String cardId) {
        this.cardIds.remove(cardId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Tag c))
            return false;

        return this.name.equals(c.getName());
    }
}