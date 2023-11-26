package ba.edu.ibu.cardshard.core.model;

import ba.edu.ibu.cardshard.core.model.enums.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    static User[] testUsers;

    @BeforeAll
    static void init() {
        testUsers = new User[2];
        testUsers[0] = new User("someId1", "Name1", "Surname1", "email1", "username1", "password1", "country1", "city1", UserType.COLLECTOR, new Date());
        testUsers[1] = new User("someId2", "Name2", "Surname2", "email2", "username2", "password2", "country2", "city2", UserType.COLLECTOR, new Date());
    }

    @Test
    void shouldBeEqual() {
        assertEquals(testUsers[0], testUsers[0]);
    }

    @Test
    void shouldNotBeEqual() {
        assertNotEquals(testUsers[0], testUsers[1]);
    }

    @Test
    void shouldCreateNewUser() {
        Assertions.assertThat(testUsers[0].getId()).isEqualTo("someId1");
        Assertions.assertThat(testUsers[0].getFirstName()).isEqualTo("Name1");
        Assertions.assertThat(testUsers[0].getLastName()).isEqualTo("Surname1");
        Assertions.assertThat(testUsers[0].getEmail()).isEqualTo("email1");
        Assertions.assertThat(testUsers[0].getUsername()).isEqualTo("username1");
        Assertions.assertThat(testUsers[0].getPassword()).isEqualTo("password1");
        Assertions.assertThat(testUsers[0].getCountry()).isEqualTo("country1");
        Assertions.assertThat(testUsers[0].getCity()).isEqualTo("city1");
        Assertions.assertThat(testUsers[0].getUserType()).isEqualTo(UserType.COLLECTOR);
    }
}
