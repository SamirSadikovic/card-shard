package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;

import java.util.HashSet;

public class CollectedCardDTO {
    private int cardId;
    private String setCode;
    private String setRarity;
    private int quantity;
    private Boolean sellTrade;
    private HashSet<String> tags;

    public CollectedCardDTO(CollectedCard collectedCard) {
        this.cardId = collectedCard.getId().getCardId();
        this.setCode = collectedCard.getId().getSetCode();
        this.setRarity = collectedCard.getId().getSetRarity();
        this.quantity = collectedCard.getQuantity();
        this.sellTrade = collectedCard.getSellTrade();
        this.tags = collectedCard.getTags();
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
