package org.uberpopug.resource.pojo.account;


public class CreateAccountRequest {

    private String login;

    private String password;

    private String parrotName;

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

    public String getParrotName() {
        return parrotName;
    }

    public void setParrotName(String parrotName) {
        this.parrotName = parrotName;
    }
}
