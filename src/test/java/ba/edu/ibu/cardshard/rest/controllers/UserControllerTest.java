package ba.edu.ibu.cardshard.rest.controllers;

import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.service.JwtService;
import ba.edu.ibu.cardshard.core.service.UserService;
import ba.edu.ibu.cardshard.rest.dto.UserDTO;
import com.jayway.jsonpath.JsonPath;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    JwtService jwtService;

    @Test
    void shouldReturnAllUsers() throws Exception {

        User user = new User();
        user.setFirstName("Samir");
        user.setLastName("Sadikovic");
        user.setCity("Tuzla");
        user.setCountry("Bosnia and Herzegovina");

        UserDTO userDTO = new UserDTO(user);

        Mockito.when(userService.getUsers()).thenReturn(List.of(userDTO));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        assertEquals("Samir", JsonPath.read(response, "$.[0].firstName"));
        assertEquals("Sadikovic", JsonPath.read(response, "$.[0].lastName"));
    }
}
