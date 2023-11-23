package ba.edu.ibu.cardshard.core.model.card;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
abstract class MonsterCard extends Card{

    protected final int atk;
    protected final String attribute;

    public MonsterCard(int id, String name, String type, String desc, String race, String setCode, String setPrice, int atk, String attribute) {
        super(id, name, type, desc, race, setCode, setPrice);
        this.atk = atk;
        this.attribute = attribute;
    }

    public int getAtk() {
        return atk;
    }

    public String getAttribute() {
        return attribute;
    }
}
