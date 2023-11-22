package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.card.Card;

import java.util.ArrayList;

public class Tag {

    private final int id;
    private final int userId;
    private String name;
    private ArrayList<Card> cards;

    public Tag(int id, int userId, String name, ArrayList<Card> cards) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getQuantity(Card card) {
        int quantity = 0;
        for (Card taggedCard : this.cards)
            if (card.getId() == taggedCard.getId() && card.getSetCode().equals(taggedCard.getSetCode()))
                quantity++;
        return quantity;
    }
}
