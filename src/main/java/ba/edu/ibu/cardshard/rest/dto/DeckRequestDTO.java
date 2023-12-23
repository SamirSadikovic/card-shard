package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.Deck;
import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;

import java.util.ArrayList;
import java.util.Date;

public class DeckRequestDTO {
    private String userId;
    private String name;
    private ArrayList<Integer> main;
    private ArrayList<Integer> extra;
    private ArrayList<Integer> side;
    private VisibilityType visibilityType;

    public DeckRequestDTO(){ }
    
    public DeckRequestDTO(Deck deck) {
        this.userId = deck.getUserId();
        this.name = deck.getName();
        this.main = deck.getMain();
        this.extra = deck.getExtra();
        this.side = deck.getSide();
        this.visibilityType = deck.getVisibilityType();
    }

    public Deck toEntity() {
        Deck deck = new Deck();
        deck.setUserId(userId);
        deck.setName(name);
        deck.setMain(main);
        deck.setExtra(extra);
        deck.setSide(side);
        deck.setVisibilityType(visibilityType);
        deck.setCreationDate(new Date());
        return deck;
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
}
