package ba.edu.ibu.cardshard.core.service;

import ba.edu.ibu.cardshard.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.cardshard.core.model.Collection;
import ba.edu.ibu.cardshard.core.model.User;
import ba.edu.ibu.cardshard.core.model.enums.UserType;
import ba.edu.ibu.cardshard.core.model.enums.VisibilityType;
import ba.edu.ibu.cardshard.core.repository.CollectionRepository;
import ba.edu.ibu.cardshard.core.repository.UserRepository;
import ba.edu.ibu.cardshard.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, CollectionRepository collectionRepository) {
        this.userRepository = userRepository;
        this.collectionRepository = collectionRepository;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        userRequestDTO.setUserType(UserType.COLLECTOR);
        User user = userRepository.save(userRequestDTO.toEntity());

        //create new collection for a newly registered user
        Collection collection = new Collection();
        collection.setUserId(user.getId());
        collection.setCards(new ArrayList<>());
        collection.setVisibilityType(VisibilityType.PUBLIC);
        collectionRepository.save(new CollectionRequestDTO(collection).toEntity());

        return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));
        String jwt = jwtService.generateToken(user);

        return new LoginDTO(jwt);
    }
}
