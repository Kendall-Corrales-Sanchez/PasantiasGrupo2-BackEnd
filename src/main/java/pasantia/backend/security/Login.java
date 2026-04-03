package pasantia.backend.security;

import pasantia.backend.entity.People;

public class Login extends People {

    private String token;

    public Login() {
    }

    public Login(Integer id, String name, String mail, String password, String token) {
        super(id, name, mail, password);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
