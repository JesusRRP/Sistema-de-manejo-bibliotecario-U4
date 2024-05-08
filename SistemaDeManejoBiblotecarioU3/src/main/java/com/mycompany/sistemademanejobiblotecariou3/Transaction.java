package com.mycompany.sistemademanejobiblotecariou3;

import java.util.Date;

/**
 *
 * @author jesusrrp
 */
public class Transaction {

    String id; //generar UID aleatoreamente hacerlo en clase utilitaria
    String type; //borrow o return
    Client client;
    Book book;
    Date date;

    public Transaction(String type, Client client, Book book, Date date) {
        this.type = type;
        this.client = client;
        this.book = book;
        this.date = date;
        this.id = UtilitaryClass.generateRandomId();
    }

}
