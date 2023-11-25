package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.model.enums.UserType;

import java.util.Date;

public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String country;
    private String city;
    private UserType userType;

    public  UserRequestDTO() { }

    public UserRequestDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.userType = user.getUserType();
    }

    public User toEntity() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setCountry(country);
        user.setCity(city);
        user.setUserType(userType);
        user.setCreationDate(new Date());
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
