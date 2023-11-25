package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.Deck;
import ba.edu.ibu.cardshard.core.model.User;

import java.util.ArrayList;
import java.util.Date;

public class DeckRequestDTO {
    private String userId;
    private String name;
    private ArrayList<Integer> main;
    private ArrayList<Integer> extra;
    private ArrayList<Integer> side;

    public DeckRequestDTO(){ }
    
    public DeckRequestDTO(String userId, String name, ArrayList<Integer> main, ArrayList<Integer> extra, ArrayList<Integer> side) {
        this.userId = userId;
        this.name = name;
        this.main = main;
        this.extra = extra;
        this.side = side;
    }

    public Deck toEntity() {
        Deck deck = new Deck();
        deck.setUserId(userId);
        deck.setName(name);
        deck.setMain(main);
        deck.setExtra(extra);
        deck.setSide(side);
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
}
