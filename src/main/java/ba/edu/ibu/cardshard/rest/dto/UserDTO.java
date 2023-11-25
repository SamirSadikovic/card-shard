package ba.edu.ibu.cardshard.rest.dto;

import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.model.enums.UserType;

import java.util.Date;

public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String username;
    private String address;
    private Date creationDate;
    private UserType userType;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.address = user.getCity() + ", " + user.getCountry();
        this.creationDate = user.getCreationDate();
        this.userType = user.getUserType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
