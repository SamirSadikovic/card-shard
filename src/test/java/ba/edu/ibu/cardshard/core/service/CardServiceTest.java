package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.model.Card;
import ba.edu.ibu.cardshard.core.model.CardSet;
import ba.edu.ibu.cardshard.core.repository.CardRepository;
import ba.edu.ibu.cardshard.rest.dto.CardDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CardServiceTest {

    @MockBean
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Test
    public void shouldReturnCardById() {

        Card card = new Card();
        card.setId(34541863);
        card.setName("\"A\" Cell Breeding Device");
        card.setType("Spell Card");
        card.setDesc("During each of your Standby Phases, put 1 A-Counter on 1 face-up monster your opponent controls.");
        card.setRace("Continuous");
        card.setArchetype("Alien");
        card.setImageLink("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/34541863.jpg");
        CardSet set = new CardSet("Force of the Breaker", "FOTB-EN043", "Common", "1.31");
        ArrayList<CardSet> sets = new ArrayList<>();
        sets.add(set);
        card.setCardSets(sets);


        Mockito.when(cardRepository.findById(34541863)).thenReturn(Optional.of(card));

        CardDTO foundCard = cardService.getCardById(34541863);
        Assertions.assertThat(foundCard.getId()).isEqualTo(34541863);
        Assertions.assertThat(foundCard.getImageLink()).isEqualTo("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/34541863.jpg");
        Assertions.assertThat(foundCard.getArchetype()).isEqualTo("Alien");
        Assertions.assertThat(foundCard.getAtk()).isEqualTo(0);
        Assertions.assertThat(foundCard.getAttribute()).isEqualTo(null);
        Assertions.assertThat(foundCard.getDef()).isEqualTo(0);
        Assertions.assertThat(foundCard.getLevel()).isEqualTo(0);
        Assertions.assertThat(foundCard.getScale()).isEqualTo(0);
        Assertions.assertThat(foundCard.getLinkVal()).isEqualTo(0);
        Assertions.assertThat(foundCard.getLinkMarkers()).isEqualTo(null);
        Assertions.assertThat(foundCard.getBanlistInfo()).isEqualTo(null);
    }

}