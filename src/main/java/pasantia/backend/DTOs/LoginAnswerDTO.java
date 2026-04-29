package pasantia.backend.DTOs;

import pasantia.backend.entity.Companies;
import pasantia.backend.entity.Users;

public class LoginAnswerDTO {
    private Integer id;
    private String name;
    private String mail;
    private String token;

    public LoginAnswerDTO(Users users, String token) {
        this.id = users.getId();
        this.name = users.getName();
        this.mail = users.getMail();
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
