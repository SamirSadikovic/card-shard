package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.Tag;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionDTO {
    private String id;
    private String userId;
    private ArrayList<CollectedCard> cards;
    private ArrayList<Tag> tags;

    public CollectionDTO(Collection collection) {
        this.id = collection.getId();
        this.userId = collection.getUserId();
        this.cards = collection.getCards();
        this.tags = collection.getTags();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
