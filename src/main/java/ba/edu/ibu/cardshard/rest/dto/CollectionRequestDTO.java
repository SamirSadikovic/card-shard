package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;

import java.util.ArrayList;

public class CollectionRequestDTO {
    private String userId;
    private ArrayList<CollectedCard> cards;
    private VisibilityType visibilityType;

    public CollectionRequestDTO() { }

    public CollectionRequestDTO(Collection collection) {
        this.userId = collection.getId();
        this.cards = collection.getCards();
        this.visibilityType = collection.getVisibilityType();
    }
    
    public Collection toEntity() {
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setCards(cards);
        collection.setVisibilityType(visibilityType);
        return collection;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<CollectedCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CollectedCard> cards) {
        this.cards = cards;
    }

    public VisibilityType getVisibilityType() {
        return visibilityType;
    }

    public void setVisibilityType(VisibilityType visibilityType) {
        this.visibilityType = visibilityType;
    }
}
