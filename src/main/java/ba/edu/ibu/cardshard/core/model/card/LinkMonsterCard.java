package ba.edu.ibu.cardshard.core.model.card;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class LinkMonsterCard extends MonsterCard {

    private final int linkVal;
    private final String[] linkMarkers;

    public LinkMonsterCard(int id, String name, String type, String desc, String race, String setCode, String setPrice, int atk, String attribute, int linkVal, String[] linkMarkers) {
        super(id, name, type, desc, race, setCode, setPrice, atk, attribute);
        this.linkVal = linkVal;
        this.linkMarkers = linkMarkers;
    }

    public int getLinkVal() {
        return linkVal;
    }

    public String[] getLinkMarkers() {
        return linkMarkers;
    }
}
