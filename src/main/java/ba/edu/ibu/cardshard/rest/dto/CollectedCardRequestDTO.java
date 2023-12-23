package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;

import java.util.HashSet;

public class CollectedCardRequestDTO {
    private String name;
    private String setName;
    private String setCode;
    private String setRarity;
    private String setPrice;
    private Boolean sellTrade;
    private HashSet<String> tags;

    public  CollectedCardRequestDTO() { }

    public CollectedCardRequestDTO(CollectedCard collectedCard) {
        this.name = collectedCard.getName();
        this.setName = collectedCard.getSetName();
        this.setCode = collectedCard.getSetCode();
        this.setRarity = collectedCard.getSetRarity();
        this.setPrice = collectedCard.getSetPrice();
        this.sellTrade = collectedCard.getSellTrade();
        this.tags = collectedCard.getTags();
    }

    public CollectedCard toEntity() {
        CollectedCard collectedCard = new CollectedCard();
        collectedCard.setName(name);
        collectedCard.setSetName(setName);
        collectedCard.setSetCode(setCode);
        collectedCard.setSetRarity(setRarity);
        collectedCard.setSetPrice(setPrice);
        collectedCard.setSellTrade(sellTrade);
        collectedCard.setTags(tags);
        return collectedCard;
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

    public void setSetPrice(String setPrice) {
        this.setPrice = setPrice;
    }

    public String getSetRarity() {
        return setRarity;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetPrice() {
        return setPrice;
    }

    public void setSetRarity(String setRarity) {
        this.setRarity = setRarity;
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
