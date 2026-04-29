package pasantia.backend.interfaces;

import pasantia.backend.DTOs.LoginAnswerDTO;
import pasantia.backend.DTOs.LoginDTO;
import pasantia.backend.entity.Users;

import java.util.List;

public interface UserService {
    Users save(Users users);
    List<Users> findAll();
    Users findById(Integer id);
    void deleteById(Integer id);
    LoginAnswerDTO login(LoginDTO request);
}
