package ba.edu.ibu.cardshard.core.model.card;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class PendulumMonsterCard extends RegularMonsterCard {

    private final int scale;

    public PendulumMonsterCard(int id, String name, String type, String desc, String race, String setCode, String setPrice, int atk, int def, int level, String attribute, int scale) {
        super(id, name, type, desc, race, setCode, setPrice, atk, def, level, attribute);
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }
}
