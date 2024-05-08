/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;

/**
 *
 * @author jesusrrp
 */
public class Administrator extends User {
    
    ArrayList <Permission> permissions = new ArrayList <Permission>();
    boolean isSuperAdmin;

    public Administrator(String name, String lastName, int year, int mont, int date, String username, String password, ArrayList <Permission> permissions) {
        super(name, lastName, year, mont, date);
        this.setPassword(password);
        this.setUsername(username);
        this.permissions = permissions;
        this.isSuperAdmin = false;
    }
    
        public Administrator(String name, String lastName, int year, int mont, int date, String username, String password, boolean isSuperAdmin, ArrayList <Permission> permissions ) {
        super(name, lastName, year, mont, date);
        this.setPassword(password);
        this.setUsername(username);
        this.isSuperAdmin = isSuperAdmin;
         this.permissions = permissions;
    }
    
        public void printPermissions(){
            System.out.println("");
            System.out.println("Permisos: ");
            for (int i = 0; i < permissions.size(); i++) {
                System.out.println(permissions.get(i));             
            }
            System.out.println("");
        }
                
        public int getAge(){
        int age = 2024 - super.birthDate.getYear();
        return age;
    }    

    public boolean isIsSuperAdmin() {
        return isSuperAdmin;
    }
        
    
    
}
