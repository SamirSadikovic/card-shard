package ba.edu.ibu.cardshard.core.repository;

import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.model.card.Card;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository(){
        this.users = Arrays.asList(
                new User("1", "Samir", "Sadikovic", "BiH", "Tuzla", new ArrayList<Card>()),
                new User("2", "Other", "User", "BiH", "Sarajevo", new ArrayList<Card>())
        );
    }

    public List<User> findAll() {
        return this.users;
    }

    public User findById(String id){
        return users.stream().filter(card -> card.getId().equals(id)).findFirst().orElse(null);
    }
}
