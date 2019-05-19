package com.website.websiteVaadin.DataUser;

public class User {

    public Long id;
    public String login, password, email;

    public User(Long _id, String _login, String _password, String _email) {
        this.id = _id;
        this.login = _login;
        this.email = _email;
        this.password = _password;
    }
    
  

    public void setId(Long id) {this.id=id;}
    public void setLogin(String s) {this.login=s;}
    public void setPassword(String s) {this.password=s;}
    public void setEMail(String s) {this.email=s;}
    
    
    public Long getId() {return this.id;}
    public String getLogin() {return this.login;}
    public String getPassword() {return this.password;}   //pamietaj gettery musza byc publiczne!!!!!!
    public String getEmail() {return this.email;}
    
    
}