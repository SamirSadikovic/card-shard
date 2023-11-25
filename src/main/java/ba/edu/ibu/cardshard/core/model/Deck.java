package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document
public class Deck {
    @Id
    private String id;
    private String userId;
    private String name;
    private ArrayList<Integer> main;
    private ArrayList<Integer> extra;
    private ArrayList<Integer> side;
    private Date creationDate;

    public Deck(){ }

    public Deck(String id, String userId, String name, ArrayList<Integer> main, ArrayList<Integer> extra, ArrayList<Integer> side, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.main = main;
        this.extra = extra;
        this.side = side;
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

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
}
