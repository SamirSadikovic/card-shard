package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.card.Card;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Document
public class User {
    @Id
    private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private ArrayList<Card> collectedCards;

    public User(String id, String firstName, String lastName, String email, String country, String city, ArrayList<Card> collectedCards) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.city = city;
        this.collectedCards = collectedCards;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Card> getCollectedCards() {
        return collectedCards;
    }

    public void setCollectedCards(ArrayList<Card> collectedCards) {
        this.collectedCards = collectedCards;
    }

    public void addToCollection(Card card) {
        this.collectedCards.add(card);
    }

    public int getQuantity(Card card){
        int quantity = 0;
        for (Card collectedCard : this.collectedCards)
            if (card.getId() == collectedCard.getId() && card.getSetCode().equals(collectedCard.getSetCode()))
                quantity++;
        return quantity;
    }

    public boolean inCollection(Card card){
        for (Card collectedCard : this.collectedCards)
            if (card.getId() == collectedCard.getId() && card.getSetCode().equals(collectedCard.getSetCode()))
                return true;
        return false;
    }
}
