package com.example.myapplication;

public class UserData {
    private String userName;
    private String email;
    private String passwd;


    public String getUserName(){
        return userName;
    }
    public void setUserName(String name){
        this.userName=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
