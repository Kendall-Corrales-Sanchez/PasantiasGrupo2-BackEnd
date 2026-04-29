package pasantia.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pasantia.backend.DTOs.LoginAnswerDTO;
import pasantia.backend.DTOs.LoginDTO;
import pasantia.backend.entity.Users;
import pasantia.backend.service.UserServiceImplement;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImplement userServiceImplement;

    public UserController(UserServiceImplement userServiceImplement) {
        this.userServiceImplement = userServiceImplement;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {

        try {
            LoginAnswerDTO response = userServiceImplement.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/register")
    public void registrar(@RequestBody Users users){
        userServiceImplement.save(users);

    }
}
