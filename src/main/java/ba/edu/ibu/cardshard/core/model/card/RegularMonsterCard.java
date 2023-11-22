package ba.edu.ibu.cardshard.core.model.card;

public class RegularMonsterCard extends MonsterCard {

    private final int def;
    private final int level;

    public RegularMonsterCard(int id, String name, String type, String desc, String race, String setCode, String setPrice, int atk, int def, int level, String attribute) {
        super(id, name, type, desc, race, setCode, setPrice, atk, attribute);
        this.def = def;
        this.level = level;
    }

    public int getDef() {
        return def;
    }

    public int getLevel() {
        return level;
    }
}
