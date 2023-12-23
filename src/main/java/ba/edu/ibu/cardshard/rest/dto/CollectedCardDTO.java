package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;

import java.util.HashSet;

public class CollectedCardDTO {

    private int id;
    private String name;
    private String setName;
    private String setCode;
    private String setRarity;
    private String setPrice;
    private Boolean sellTrade;
    private HashSet<String> tags;

    public CollectedCardDTO(CollectedCard collectedCard) {
        this.id = collectedCard.getId();
        this.name = collectedCard.getName();
        this.setName = collectedCard.getSetName();
        this.setCode = collectedCard.getSetCode();
        this.setRarity = collectedCard.getSetRarity();
        this.setPrice = collectedCard.getSetPrice();
        this.sellTrade = collectedCard.getSellTrade();
        this.tags = collectedCard.getTags();
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

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetRarity() {
        return setRarity;
    }

    public void setSetRarity(String setRarity) {
        this.setRarity = setRarity;
    }

    public String getSetPrice() {
        return setPrice;
    }

    public void setSetPrice(String setPrice) {
        this.setPrice = setPrice;
    }

    public Boolean getSellTrade() {
        return sellTrade;
    }

    public void setSellTrade(Boolean sellTrade) {
        this.sellTrade = sellTrade;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }
}
