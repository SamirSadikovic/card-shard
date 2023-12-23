package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection="decks")
public class Deck {
    @Id
    private String id;
    private String userId;
    private String name;
    private ArrayList<Integer> main;
    private ArrayList<Integer> extra;
    private ArrayList<Integer> side;
    private VisibilityType visibilityType;
    private Date creationDate;

    public Deck(){ }

    public Deck(String id, String userId, String name, ArrayList<Integer> main, ArrayList<Integer> extra, ArrayList<Integer> side, VisibilityType visibilityType, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.main = main;
        this.extra = extra;
        this.side = side;
        this.visibilityType = visibilityType;
        this.creationDate = creationDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getMain() {
        return main;
    }

    public void setMain(ArrayList<Integer> main) {
        this.main = main;
    }

    public ArrayList<Integer> getExtra() {
        return extra;
    }

    public void setExtra(ArrayList<Integer> extra) {
        this.extra = extra;
    }

    public ArrayList<Integer> getSide() {
        return side;
    }

    public void setSide(ArrayList<Integer> side) {
        this.side = side;
    }

    public VisibilityType getVisibilityType() {
        return visibilityType;
    }

    public void setVisibilityType(VisibilityType visibilityType) {
        this.visibilityType = visibilityType;
    }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public int getMainSize() { return this.main.size(); }

    public int getExtraSize() { return this.extra.size(); }

    public int getSideSize() { return this.side.size(); }

    public void addToMain(int cardId) { this.main.add(cardId); }

    public void addToExtra(int cardId) { this.extra.add(cardId); }

    public void addToSide(int cardId) { this.side.add(cardId); }

    public void removeFromMain(int cardId) { this.main.remove(cardId); }

    public void removeFromExtra(int cardId) { this.extra.remove(cardId); }

    public void removeFromSide(int cardId) { this.side.remove(cardId); }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Deck d))
            return false;

        return this.id.equals(d.getId());
    }
}
