package pasantia.backend.DTOs;

import pasantia.backend.entity.Companies;

public class LoginAnswerDTO {
    private Integer id;
    private String name;
    private String mail;
    private String whoAreWe;
    private String token;

    public LoginAnswerDTO(Companies company, String token) {
        this.id = company.getId();
        this.name = company.getName();
        this.mail = company.getMail();
        this.whoAreWe = company.getWhoAreWe();
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

    public String getWhoAreWe() {
        return whoAreWe;
    }

    public void setWhoAreWe(String whoAreWe) {
        this.whoAreWe = whoAreWe;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
