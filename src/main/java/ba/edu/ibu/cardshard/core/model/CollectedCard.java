package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document(collection="collections")
public class CollectedCard {
    private CollectedCardId id;
    private int quantity;
    private Boolean sellTrade;
    private HashSet<String> tags;

    public CollectedCard(){ }

    public CollectedCard(int cardId, String setCode, String setRarity, int quantity, Boolean sellTrade, HashSet<String> tags) {
        this.id = new CollectedCardId(cardId, setCode, setRarity);
        this.quantity = quantity;
        this.sellTrade = sellTrade;
        this.tags = tags;
    }

    public void updateCard(CollectedCard newCard) {
        this.id = newCard.getId();
        this.quantity = newCard.getQuantity();
        this.sellTrade = newCard.getSellTrade();
        this.tags = newCard.getTags();
    }

    public CollectedCardId getId() { return id; }

    public void setId(CollectedCardId id) { this.id = id; }

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

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void dropTag(String tag) {
        this.tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof CollectedCard c))
            return false;

        return (this.id.equals(c.getId()));
    }

    public static class CollectedCardId {
        private int cardId;
        private String setCode;
        private String setRarity;

        public CollectedCardId(int cardId, String setCode, String setRarity) {
            this.cardId = cardId;
            this.setCode = setCode;
            this.setRarity = setRarity;
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

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;

            if (!(o instanceof CollectedCardId cid))
                return false;

            return (this.cardId == cid.getCardId() && this.setCode.equals(cid.getSetCode()) && this.setRarity.equals(cid.getSetRarity()));
        }
    }
}
