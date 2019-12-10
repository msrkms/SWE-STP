package com.sajidur.swe_stp.Backend.Model;

public class User {
    private String Email;
    private String ID;
    private String Password;
    private String VCode;
    private String Role;
    private String IsVerified;

    public User(String Email,String Password){
        this.setEmail(Email);
        this.setPassword(Password);
    }

    public User(String Email,String ID,String Password){
        this.setEmail(Email);
        this.setID(ID);
        this.setPassword(Password);
    }

    public  User(){

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getVcode() {
        return VCode;
    }

    public void setVcode(String vcode) {
        VCode = vcode;
    }

    public String getIsVerified() {
        return IsVerified;
    }

    public void setIsVerified(String isVerified) {
        IsVerified = isVerified;
    }


    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
