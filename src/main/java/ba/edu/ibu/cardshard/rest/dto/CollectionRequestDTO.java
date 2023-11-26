package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;
import ba.edu.ibu.cardshard.core.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CollectionRequestDTO {
    private String userId;
    private ArrayList<CollectedCard> cards;
    private ArrayList<Tag> tags;

    public CollectionRequestDTO() { }

    public CollectionRequestDTO(Collection collection) {
        this.userId = collection.getId();
        this.cards = collection.getCards();
        this.tags = collection.getTags();
    }
    
    public Collection toEntity() {
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setCards(cards);
        collection.setTags(tags);
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
