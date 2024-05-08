/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

/**
 *
 * @author jesusrrp
 */
public abstract class User  extends Profile{
  
    private String username;
    private String password;

    public User(String name, String lastName, int year, int mont, int date) {
        super(name, lastName, year, mont, date);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = Login.sha256(password);
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    

}
