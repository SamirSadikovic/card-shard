package ba.edu.ibu.cardshard.core.model.card;

public abstract class Card {

    protected final int id;
    protected final String name;
    protected final String type;
    protected final String desc;
    protected final String race;
    protected final String setCode;
    protected final String setPrice;

    public Card(int id, String name, String type, String desc, String race, String setCode, String setPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.race = race;
        this.setCode = setCode;
        this.setPrice = setPrice;
    }

    public int getId() {
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
}
