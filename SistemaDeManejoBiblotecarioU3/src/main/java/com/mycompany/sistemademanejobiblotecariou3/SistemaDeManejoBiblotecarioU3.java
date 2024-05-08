package com.mycompany.sistemademanejobiblotecariou3;

/**
 *
 * @author jesusrrp
 */
public class SistemaDeManejoBiblotecarioU3 {

    public static void main(String[] args) {
        Seeder.initialize(); //crea algunos objetos 
        Login.createNewUsersArray();
        Login.initialize();

    }
}
