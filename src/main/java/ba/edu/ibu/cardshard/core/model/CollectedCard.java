package ba.edu.ibu.cardshard.core.model;

public class CollectedCard {
    private int id;
    private String setCode;

    public CollectedCard(int id, String setCode) {
        this.id = id;
        this.setCode = setCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
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
