package ba.edu.ibu.cardshard.core.model;

public class CardSet {
    private String setName;
    private String setCode;
    private String setRarity;
    private String setPrice;

    public CardSet() { }

    public CardSet(String setName, String setCode, String setRarity, String setPrice) {
        this.setName = setName;
        this.setCode = setCode;
        this.setRarity = setRarity;
        this.setPrice = setPrice;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof CardSet c))
            return false;

        return this.setName.equals(c.getSetName());
    }
}
