package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.Deck;

import java.util.ArrayList;
import java.util.Date;

public class DeckDTO {
    private String id;
    private String userId;
    private String name;
    private ArrayList<Integer> main;
    private ArrayList<Integer> extra;
    private ArrayList<Integer> side;
    private Date creationDate;

    public DeckDTO(Deck deck) {
        this.id = deck.getId();
        this.userId = deck.getUserId();
        this.name = deck.getName();
        this.main = deck.getMain();
        this.extra = deck.getExtra();
        this.side = deck.getSide();
        this.creationDate = deck.getCreationDate();
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

    public String getName() { return name; }

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

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
}
