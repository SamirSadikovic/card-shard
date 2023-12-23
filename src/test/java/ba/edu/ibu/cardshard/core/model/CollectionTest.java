package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.enums.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.jayway.jsonpath.internal.function.Parameter.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.common.NormalizedString.toArrayList;

public class CollectionTest {
    static Collection[] testCollections;

    @BeforeAll
    static void init() {
        testCollections = new Collection[2];

        ArrayList<CollectedCard> cards1 = new ArrayList<>();
        cards1.add(new CollectedCard(33396948, "LOB-EN124", true));

        ArrayList<CollectedCard> cards2 = new ArrayList<>();
        cards2.add(new CollectedCard(70903634, "LOB-EN122", true));

        ArrayList<Tag> tags1 = new ArrayList<>();
        ArrayList<Integer> cardIds1 = new ArrayList<>();
        cardIds1.add(8124921);
        cardIds1.add(7902349);
        tags1.add(new Tag("new", cardIds1));

        ArrayList<Tag> tags2 = new ArrayList<>();
        ArrayList<Integer> cardIds2 = new ArrayList<>();
        cardIds2.add(33396948);
        cardIds2.add(70903634);
        tags2.add(new Tag("old", cardIds2));

        testCollections[0] = new Collection("someId1", "someUserId1", cards1, tags1);
        testCollections[1] = new Collection("someId2", "someUserId2", cards2, tags2);
    }

    @Test
    void shouldBeEqual() {
        assertEquals(testCollections[0], testCollections[0]);
    }

    @Test
    void shouldNotBeEqual() {
        assertNotEquals(testCollections[0], testCollections[1]);
    }

    @Test
    void shouldCreateNewCollection() {
        Assertions.assertThat(testCollections[0].getId()).isEqualTo("someId1");
        Assertions.assertThat(testCollections[0].getUserId()).isEqualTo("someUserId1");
        Assertions.assertThat(testCollections[0].getCards().get(0)).isEqualTo(new CollectedCard(33396948, "LOB-EN124", true));
    }

    @Test
    void shouldAddCard() {
        testCollections[0].addCard(new CollectedCard(44519536, "LOB-EN121", false));
        Assertions.assertThat(testCollections[0].getCards().get(1)).isEqualTo(new CollectedCard(44519536, "LOB-EN121", false));
    }

    @Test
    void shouldGetCollectionSize() {
        Assertions.assertThat(testCollections[0].getCollectionSize()).isEqualTo(1);
    }

    @Test
    void shouldAddTag() {
        ArrayList<Integer> cardIds = new ArrayList<>();
        cardIds.add(8124921);
        cardIds.add(7902349);
        Tag tag = new Tag("newTag", cardIds);

        testCollections[0].addTag(tag);
        Assertions.assertThat(testCollections[0].getTags().get(1)).isEqualTo(tag);
    }

    @Test
    void shouldDropTag() {
        Tag tag = new Tag("new", new ArrayList<>());
        testCollections[0].dropTag(tag);
        Assertions.assertThat(testCollections[0].getTags().size()).isEqualTo(0);
    }
}
