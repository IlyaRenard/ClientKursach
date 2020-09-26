package by.bsuir.pokos.message;


import java.io.Serializable;

/**
 * Created by Gavrilik on 18.09.2020.
 */
public class SignInDATA implements Serializable {

    static final long serialVersionUID = 3432063776451490808L;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
