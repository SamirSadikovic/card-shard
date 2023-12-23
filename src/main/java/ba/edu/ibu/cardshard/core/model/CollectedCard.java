package ba.edu.ibu.cardshard.core.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection="collections")
public class CollectedCard {
    private int id;
    private String setCode;
    private Boolean sellTrade;

    public CollectedCard(int id, String setCode, Boolean sellTrade) {
        this.id = id;
        this.setCode = setCode;
        this.sellTrade = sellTrade;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public Boolean getSellTrade() {
        return sellTrade;
    }

    public void setSellTrade(Boolean sellTrade) {
        this.sellTrade = sellTrade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof CollectedCard c))
            return false;

        return (this.id == c.getId() && this.setCode.equals(c.getSetCode()));
    }
}
