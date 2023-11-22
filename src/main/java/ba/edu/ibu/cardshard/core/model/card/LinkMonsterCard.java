package ba.edu.ibu.cardshard.core.model.card;

public class LinkMonsterCard extends MonsterCard {

    private final int linkval;
    private final String[] linkmarkers;

    public LinkMonsterCard(int id, String name, String type, String desc, String race, String setCode, String setPrice, int atk, String attribute, int linkval, String[] linkmarkers) {
        super(id, name, type, desc, race, setCode, setPrice, atk, attribute);
        this.linkval = linkval;
        this.linkmarkers = linkmarkers;
    }

    public int getLinkval() {
        return linkval;
    }

    public String[] getLinkmarkers() {
        return linkmarkers;
    }
}
