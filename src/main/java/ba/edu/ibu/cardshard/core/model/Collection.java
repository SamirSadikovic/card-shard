package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;

@Document(collection="collections")
public class Collection {
    @Id
    private String id;
    private String userId;
    private ArrayList<CollectedCard> cards;
    private ArrayList<Tag> tags;

    public Collection() { }
    public Collection(String id, String userId, ArrayList<CollectedCard> cards, ArrayList<Tag> tags) {
        this.id = id;
        this.userId = userId;
        this.cards = cards;
        this.tags = tags;
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

    public void addCard(CollectedCard card) {
        this.cards.add(card);
    }

    public void removeCard(CollectedCard card) {
        this.cards.remove(card);
    }

    public int getCollectionSize() { return this.cards.size(); }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void dropTag(Tag tag) {
        this.tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Collection c))
            return false;

        return this.id.equals(c.getId());
    }
}
