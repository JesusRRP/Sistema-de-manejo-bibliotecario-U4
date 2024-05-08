package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;

/**
 *
 * @author jesusrrp
 */
public class Client extends User {

    ArrayList<Book> borrowedBooks;

    public Client(String name, String lastName, int year, int mont, int date) {
        super(name, lastName, year, mont, date);
         this.borrowedBooks = new ArrayList<Book>();
    }
    
       public Client(String name, String lastName, int year, int mont, int date, String username, String password) {
        super(name, lastName, year, mont, date);
         this.borrowedBooks = new ArrayList<Book>();
         this.setUsername(username);
         this.setPassword(password);
    }

    public int getAge(){
        int age = 2024 - super.birthDate.getYear();
        return age;
    }    
    
        public void addBorrowedBookbyDefault() {
        borrowedBooks.add(new Book("123321", "Dialogos de juan", new Author("juan1", "perez1", 1890, 8, 10), 2000, 2, 3)); //Metodo provicional paara añadir libros
    }

}
