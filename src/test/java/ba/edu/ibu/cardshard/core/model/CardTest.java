package ba.edu.ibu.cardshard.core.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    static Card[] testCards;

    @BeforeAll
    static void init() {
        testCards = new Card[4];
        
        for (int i = 0; i < 4; i++){
            testCards[i] = new Card();
        }

        CardSet set = new CardSet();

        testCards[0].setId(4206964);
        testCards[0].setName("Trap Hole");
        testCards[0].setType("Trap Card");
        testCards[0].setDesc("When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.");
        testCards[0].setRace("Normal");
        testCards[0].setArchetype("Hole");
        testCards[0].setImageLink("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/4206964.jpg");
        set.setSetName("2-Player Starter Deck: Yuya & Declan");
        set.setSetCode("YS15-ENY17");
        set.setSetRarity("Common");
        set.setSetPrice("4.1");
        ArrayList<CardSet> sets0 = new ArrayList<>();
        sets0.add(set);
        testCards[0].setCardSets(sets0);
        
        testCards[1].setId(70095154);
        testCards[1].setName("Cyber Dragon");
        testCards[1].setType("Effect Monster");
        testCards[1].setDesc("If only your opponent controls a monster, you can Special Summon this card (from your hand).");
        testCards[1].setRace("Machine");
        testCards[1].setAttribute("LIGHT");
        testCards[1].setArchetype("Cyber Dragon");
        testCards[1].setImageLink("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/70095154.jpg");
        testCards[1].setAtk(2100);
        testCards[1].setDef(1600);
        testCards[1].setLevel(4);
        set.setSetName("Force of the Breaker");
        set.setSetCode("FOTB-ENY04");
        set.setSetRarity("Common");
        set.setSetPrice("8.22");
        ArrayList<CardSet> sets1 = new ArrayList<>();
        sets1.add(set);
        testCards[1].setCardSets(sets1);

        testCards[2].setId(52296675);
        testCards[2].setName("Anchamoufrite");
        testCards[2].setType("Pendulum Effect Monster");
        testCards[2].setDesc("[ Pendulum Effect ] \\nIf you have no cards in your Extra Deck except \\\"Anchamoufrite\\\": You can destroy this card, then draw 1 card. You can only use this effect of \\\"Anchamoufrite\\\" once per turn.\\n[ Monster Effect ] \\nCannot be Normal Summoned/Set. Must be Special Summoned (from your face-up Extra Deck) by having no cards in your Extra Deck except \\\"Anchamoufrite\\\". You can only Special Summon \\\"Anchamoufrite\\\" once per turn this way. If this face-up card in the Monster Zone leaves the field, banish it.");
        testCards[2].setRace("Spellcaster");
        testCards[2].setAttribute("LIGHT");
        testCards[2].setImageLink("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/52296675.jpg");
        testCards[2].setAtk(1800);
        testCards[2].setDef(0);
        testCards[2].setLevel(4);
        testCards[2].setScale(4);
        set.setSetName("Lightning Overdrive");
        set.setSetCode("LIOV-EN026");
        set.setSetRarity("Common");
        set.setSetPrice("1.3");
        ArrayList<CardSet> sets2 = new ArrayList<>();
        sets2.add(set);
        testCards[2].setCardSets(sets2);

        testCards[3].setId(86066372);
        testCards[3].setName("Accesscode Talker");
        testCards[3].setType("Link Monster");
        testCards[3].setDesc("2+ Effect Monsters\\r\\nYour opponent cannot activate cards or effects in response to this card's effect activations. If this card is Link Summoned: You can target 1 Link Monster that was used as material for its Link Summon; this card gains ATK equal to that monster's Link Rating x 1000. You can banish 1 Link Monster from your field or GY; destroy 1 card your opponent controls, also for the rest of this turn, you cannot banish monsters with that same Attribute to activate this effect of \\\"Accesscode Talker\\\".");
        testCards[3].setRace("Cyberse");
        testCards[3].setAttribute("DARK");
        testCards[3].setArchetype("Code Talker");
        testCards[3].setImageLink("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/86066372.jpg");
        testCards[3].setAtk(2300);
        testCards[3].setLinkVal(4);
        ArrayList<String> linkMarkers = new ArrayList<>();
        linkMarkers.add("Top");
        linkMarkers.add("Left");
        linkMarkers.add("Bottom");
        linkMarkers.add("Right");
        testCards[3].setLinkMarkers(linkMarkers);
        set.setSetName("Force of the Breaker");
        set.setSetCode("FOTB-EN043");
        set.setSetRarity("Common");
        set.setSetPrice("1.31");
        ArrayList<CardSet> sets3 = new ArrayList<>();
        sets3.add(set);
        testCards[3].setCardSets(sets3);
    }

    private static Stream<Arguments> provideCardsForShouldBeEqual() {
        testCards[0].setId(55144522);
        testCards[0].setId(55144522);
        testCards[2].setId(46986414);
        testCards[3].setId(46986414);

        return Stream.of(
                Arguments.of(testCards[0], testCards[0], true),
                Arguments.of(testCards[2], testCards[3], true),
                Arguments.of(testCards[0], testCards[3], false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsForShouldBeEqual")
    void shouldBeEqual(Card c1, Card c2, boolean expected) {
        assertEquals(expected, c1.equals(c2));
    }

    @Test
    void shouldCreateNewCard() {
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

        Assertions.assertThat(card.getId()).isEqualTo(34541863);
        Assertions.assertThat(card.getName()).isEqualTo("\"A\" Cell Breeding Device");
        Assertions.assertThat(card.getType()).isEqualTo("Spell Card");
        Assertions.assertThat(card.getDesc()).isEqualTo("During each of your Standby Phases, put 1 A-Counter on 1 face-up monster your opponent controls.");
        Assertions.assertThat(card.getRace()).isEqualTo("Continuous");
        Assertions.assertThat(card.getArchetype()).isEqualTo("Alien");
        Assertions.assertThat(card.getImageLink()).isEqualTo("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/34541863.jpg");
        Assertions.assertThat(card.getCardSets().get(0)).isEqualTo(new CardSet("Force of the Breaker", "FOTB-EN043", "Common", "1.31"));
        Assertions.assertThat(card.getAtk()).isEqualTo(0);
        Assertions.assertThat(card.getAttribute()).isEqualTo(null);
        Assertions.assertThat(card.getDef()).isEqualTo(0);
        Assertions.assertThat(card.getLevel()).isEqualTo(0);
        Assertions.assertThat(card.getScale()).isEqualTo(0);
        Assertions.assertThat(card.getLinkVal()).isEqualTo(0);
        Assertions.assertThat(card.getLinkMarkers()).isEqualTo(null);
        Assertions.assertThat(card.getBanlistInfo()).isEqualTo(null);
    }
}
