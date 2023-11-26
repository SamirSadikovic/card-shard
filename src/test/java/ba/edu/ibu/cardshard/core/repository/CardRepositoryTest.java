package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void shouldReturnAllCards() {
        List<Card> cards = cardRepository.findAll();

        Assertions.assertEquals(12850, cards.size());
        Assertions.assertEquals("\"A\" Cell Breeding Device", cards.get(0).getName());
    }

    @Test
    public void shouldFindCardById() {
        Optional<Card> card = cardRepository.findById(34541863);
        Assertions.assertNotNull(card.orElse(null));
    }
}
