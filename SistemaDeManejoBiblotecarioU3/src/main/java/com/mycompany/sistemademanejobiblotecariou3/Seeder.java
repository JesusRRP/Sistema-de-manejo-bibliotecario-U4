/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

/**
 *
 * @author jesusrrp
 */
public class Seeder {

    public static void initialize() {
        AuthorRepository.createSomeAuthors();
        BookRepository.createSomeBooks();
        ClientRepository.createSomeClients();
        AdministratorRepository.createSomeAdmins();
        TransactionExecution.createSomeFakeTransactions();

    }

}
