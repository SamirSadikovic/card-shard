package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Document(collection="collections")
public class Tag {
    private String name;
    private ArrayList<Integer> cards;

    public Tag() { }

    public Tag(String name, ArrayList<Integer> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
    }

    public void addCard(int card) {
        this.cards.add(card);
    }

    public void removeCard(int card) {
        this.cards.remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Tag t))
            return false;

        return this.name.equals(t.getName());
    }
}