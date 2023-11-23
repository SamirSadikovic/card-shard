package ba.edu.ibu.cardshard.core.model.card;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
public class SkillCard extends Card{

    public SkillCard(int id, String name, String type, String desc, String race, String setCode, String setPrice) {
        super(id, name, type, desc, race, setCode, setPrice);
    }
}
