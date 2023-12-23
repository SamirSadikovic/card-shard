package ba.edu.ibu.cardshard.core.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeckTest {

    static Deck[] testDecks;

    @BeforeAll
    static void init() {
        testDecks = new Deck[2];

        ArrayList<Integer> main1 = new ArrayList<>();
        main1.add(8124921);
        main1.add(7902349);
        ArrayList<Integer> main2 = new ArrayList<>();
        main2.add(86198326);
        main2.add(14261867);

        ArrayList<Integer> extra1 = new ArrayList<>();
        extra1.add(49140998);
        extra1.add(68170903);
        ArrayList<Integer> extra2 = new ArrayList<>();
        extra2.add(86198326);
        extra2.add(14261867);

        ArrayList<Integer> side1 = new ArrayList<>();
        side1.add(86198326);
        side1.add(14261867);
        ArrayList<Integer> side2 = new ArrayList<>();
        side2.add(86198326);
        side2.add(14261867);

        testDecks[0] = new Deck("someId1", "someUserId1", "someName1", main1, extra1, side1, new Date());
        testDecks[1] = new Deck("someId2", "someUserId2", "someName2", main2, extra2, side2, new Date());
    }

    @Test
    void shouldBeEqual() {
        assertEquals(testDecks[0], testDecks[0]);
    }

    @Test
    void shouldNotBeEqual() {
        assertNotEquals(testDecks[0], testDecks[1]);
    }

    @Test
    void shouldCreateNewDeck() {
        Assertions.assertThat(testDecks[0].getId()).isEqualTo("someId1");
        Assertions.assertThat(testDecks[0].getUserId()).isEqualTo("someUserId1");
        Assertions.assertThat(testDecks[0].getName()).isEqualTo("someName1");
        Assertions.assertThat(testDecks[0].getMain().get(0)).isEqualTo(8124921);
        Assertions.assertThat(testDecks[0].getExtra().get(0)).isEqualTo(49140998);
        Assertions.assertThat(testDecks[0].getSide().get(0)).isEqualTo(86198326);
    }

    @Test
    void shouldAddToMain() {
        testDecks[0].addToMain(8949584);
        Assertions.assertThat(testDecks[0].getMain().contains(8949584));
    }

    @Test
    void shouldAddToExtra() {
        testDecks[0].addToExtra(8949584);
        Assertions.assertThat(testDecks[0].getExtra().contains(8949584));
    }

    @Test
    void shouldAddToSide() {
        testDecks[0].addToSide(8949584);
        Assertions.assertThat(testDecks[0].getSide().contains(8949584));
    }
}
