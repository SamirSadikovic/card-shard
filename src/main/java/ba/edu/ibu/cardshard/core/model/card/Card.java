package ba.edu.ibu.cardshard.core.model.card;

import ba.edu.ibu.cardshard.core.model.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Inherited;

@Document(collection = "cards")
public abstract class Card {

    @Id
    protected final String id;
    protected final String name;
    protected final String type;
    protected final String desc;
    protected final String race;
    protected final String setCode;
    protected final String setPrice;

    public Card(String id, String name, String type, String desc, String race, String setCode, String setPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.race = race;
        this.setCode = setCode;
        this.setPrice = setPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getRace() {
        return race;
    }

    public String getSetCode() {
        return setCode;
    }

    public String getSetPrice() {
        return setPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Card c))
            return false;

        return this.id.equals(c.getId());
    }
}
