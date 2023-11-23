package ba.edu.ibu.cardshard.core.repository;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    private List<String> users;

    public UserRepository(){
        this.users = Arrays.asList("User1", "User2", "User3");
    }

    public List<String> findAll() {
        return this.users;
    }

    private String findById(int id){
        return this.users.get(id);
    }
}
