package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;

@Document
public class Card {
    @Id
    private int id;
    private String name;
    private String type;
    private String desc;
    private String race;
    private String archetype;
    private int atk;
    private String attribute;
    private int def;
    private int level;
    private int scale;
    private int linkVal;
    private ArrayList<String> linkMarkers;
    private ArrayList<CardSet> cardSets;
    private String imageLink;
    private HashMap<String, String> banlistInfo;

    public Card(int id, String name, String type, String desc, String race, String archetype, int atk, String attribute, int def, int level, int scale, int linkVal, ArrayList<String> linkMarkers, ArrayList<CardSet> cardSets, String imageLink, HashMap<String, String> banlistInfo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.race = race;
        this.archetype = archetype;
        this.atk = atk;
        this.attribute = attribute;
        this.def = def;
        this.level = level;
        this.scale = scale;
        this.linkVal = linkVal;
        this.linkMarkers = linkMarkers;
        this.cardSets = cardSets;
        this.imageLink = imageLink;
        this.banlistInfo = banlistInfo;
    }

    public Card() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getLinkVal() {
        return linkVal;
    }

    public void setLinkVal(int linkVal) {
        this.linkVal = linkVal;
    }

    public ArrayList<String> getLinkMarkers() {
        return linkMarkers;
    }

    public void setLinkMarkers(ArrayList<String> linkMarkers) {
        this.linkMarkers = linkMarkers;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public HashMap<String, String> getBanlistInfo() {
        return banlistInfo;
    }

    public void setBanlistInfo(HashMap<String, String> banlistInfo) {
        this.banlistInfo = banlistInfo;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public ArrayList<CardSet> getCardSets() {
        return cardSets;
    }

    public void setCardSets(ArrayList<CardSet> cardSets) {
        this.cardSets = cardSets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Card c))
            return false;

        return this.id == c.getId();
    }
}
