package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.model.CardSet;

import java.util.ArrayList;
import java.util.HashMap;

public class CardDTO {
    private int id;
    private String name;
    private String type;
    private String desc;
    private String race;
    private String imageLink;
    private ArrayList<CardSet> cardSets;
    private String archetype;
    private int atk;
    private String attribute;
    private int def;
    private int level;
    private int scale;
    private int linkVal;
    private ArrayList<String> linkMarkers;
    private HashMap<String, String> banlistInfo;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.type = card.getType();
        this.desc = card.getDesc();
        this.race = card.getRace();
        this.imageLink = card.getImageLink();
        this.cardSets = card.getCardSets();
        this.archetype = card.getArchetype();
        this.atk = card.getAtk();
        this.attribute = card.getAttribute();
        this.def = card.getDef();
        this.level = card.getLevel();
        this.scale = card.getScale();
        this.linkVal = card.getLinkVal();
        this.linkMarkers = card.getLinkMarkers();
        this.banlistInfo = card.getBanlistInfo();
    }

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

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
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

    public int getLinkVal() { return linkVal; }

    public void setLinkVal(int linkVal) {
        this.linkVal = linkVal;
    }

    public ArrayList<String> getLinkMarkers() {
        return linkMarkers;
    }

    public void setLinkMarkers(ArrayList<String> linkMarkers) {
        this.linkMarkers = linkMarkers;
    }

    public ArrayList<CardSet> getCardSets() {
        return cardSets;
    }

    public void setCardSets(ArrayList<CardSet> cardSets) {
        this.cardSets = cardSets;
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
}
