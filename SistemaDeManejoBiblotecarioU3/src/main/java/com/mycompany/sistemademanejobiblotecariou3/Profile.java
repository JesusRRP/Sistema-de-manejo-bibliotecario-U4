package com.mycompany.sistemademanejobiblotecariou3;

import java.util.Date;

/**
 *
 * @author jesusrrp
 */
public class Profile {

    String name;
    String lastName;
    Date birthDate;

    public Profile(String name, String lastName, int year, int mont, int date) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = new Date(year, mont, date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

    
}
