package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;

@Document
public class Collection {
    @Id
    private final String id;
    private String userId;
    private ArrayList<Card> collectedCards;
    private HashSet<Tag> tags;

    @Autowired
    public Collection(String id, String userId, ArrayList<Card> collectedCards, HashSet<Tag> tags) {
        this.id = id;
        this.userId = userId;
        this.collectedCards = collectedCards;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public void setCollectedCards(ArrayList<Card> collectedCards) {
        this.collectedCards = collectedCards;
    }

    public void addCard(Card card) {
        this.collectedCards.add(card);
    }

    public void removeCard(Card card) {
        this.collectedCards.remove(card);
    }

    public int getCollectionSize() { return this.collectedCards.size(); }

    public HashSet<Tag> getTags() {
        return tags;
    }

    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void dropTag(Tag tag) {
        this.tags.remove(tag);
    }
}
