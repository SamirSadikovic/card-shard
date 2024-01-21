package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.CollectedCard;

import java.util.HashSet;

public class CollectedCardRequestDTO {
    private CollectedCard.CollectedCardId id;
    private int quantity;
    private Boolean sellTrade;
    private HashSet<String> tags;

    public  CollectedCardRequestDTO() { }

    public CollectedCardRequestDTO(CollectedCard collectedCard) {
        this.id = collectedCard.getId();
        this.quantity = collectedCard.getQuantity();
        this.sellTrade = collectedCard.getSellTrade();
        this.tags = collectedCard.getTags();
    }

    public CollectedCard toEntity() {
        CollectedCard collectedCard = new CollectedCard();
        collectedCard.setId(id);
        collectedCard.setQuantity(quantity);
        collectedCard.setSellTrade(sellTrade);
        collectedCard.setTags(tags);
        return collectedCard;
    }

    public CollectedCard.CollectedCardId getId() {
        return id;
    }

    public void setId(CollectedCard.CollectedCardId id) {
        this.id = id;
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
