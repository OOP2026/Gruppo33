package model;

/**
 * The type Utente.
 */
public class Utente {
    private String login;
    private final String password;

    /**
     * Instantiates a new Utente.
     *
     * @param login    the login
     * @param password the password
     */
    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Login boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public boolean login(String login, String password) {
        return ( login.equals(this.login) && password.equals(this.password));
    }

    public  String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }



}
