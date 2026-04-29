package pasantia.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pasantia.backend.DTOs.LoginAnswerDTO;
import pasantia.backend.DTOs.LoginDTO;
import pasantia.backend.entity.Users;
import pasantia.backend.interfaces.UserService;
import pasantia.backend.repository.UserRepository;
import pasantia.backend.security.JwtService;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder encoder;

    public UserServiceImplement(UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public LoginAnswerDTO login(LoginDTO request) {
        Users users = userRepository.findByMail(request.getMail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));


        boolean passwordValid = encoder.matches(request.getPassword(), users.getPassword());

        if (!passwordValid) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Genera token
        String token = jwtService.generateToken(users);
        return new LoginAnswerDTO(users, token);
    }
}
