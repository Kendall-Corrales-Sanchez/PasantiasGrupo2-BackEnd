package pasantia.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pasantia.backend.entity.Companies;
import pasantia.backend.security.Login;
import pasantia.backend.serviceImplement.CompanyServiceImplement;

import java.util.List;

@RestController
@RequestMapping("/company/probate")
public class CompanyController {

    private final CompanyServiceImplement companyServiceImplement;
    public CompanyController(CompanyServiceImplement companyServiceImplement) {
        this.companyServiceImplement = companyServiceImplement;
    }

    @PostMapping
    public Companies save(@RequestBody Companies company){
        return companyServiceImplement.save(company);
    }

    @GetMapping
    public List<Companies> findAll(){
        return companyServiceImplement.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        companyServiceImplement.deleteById(id);
    }
    @PutMapping("/{id}")
    public Companies update(@RequestBody Companies company, @PathVariable Integer id){
        Companies update = companyServiceImplement.findById(id);
        update.setName(company.getName());
        update.setMail(company.getMail());
        update.setPassword(company.getPassword());
        update.setWhoAreWe(company.getWhoAreWe());
        return companyServiceImplement.update(update);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login loginRequest) {
        System.out.println("Mail recibido: " + loginRequest.getMail());
        System.out.println("Password recibido: " + loginRequest.getPassword());

        try {
            Login response = companyServiceImplement.login(loginRequest.getMail(), loginRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}
