package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="collections")
public class Collection {
    @Id
    private String id;
    private String userId;
    private ArrayList<CollectedCard> cards;
    private VisibilityType visibilityType;

    public Collection() { }
    public Collection(String id, String userId, ArrayList<CollectedCard> cards, VisibilityType visibilityType) {
        this.id = id;
        this.userId = userId;
        this.cards = cards;
        this.visibilityType = visibilityType;
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

    public VisibilityType getVisibilityType() {
        return visibilityType;
    }

    public void setVisibilityType(VisibilityType visibilityType) {
        this.visibilityType = visibilityType;
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
