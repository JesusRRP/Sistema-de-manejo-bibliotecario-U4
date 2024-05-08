package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;

/**
 *
 * @author jesusrrp
 */
public class Author extends Profile {

    ArrayList<Book> books; //libros que el autor ha escrito

    public Author(String name, String lastName, int year, int mont, int date) {
        super(name, lastName, year, mont, date);
        this.books = new ArrayList<Book>();
    }

    public void setNewBook(Book book) {
        books.add(book);
    }

    public void removeBook(int index) {
        books.remove(index);
    }

    public int getIndexOfBook(String isbn) {
        books.get(0);
        int index = 0;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == isbn) {
                index = i;

            }
        }
        System.out.println("Autor anterior borrado con exito");
        return index;
    }
    
        public int getAge(){
        int age = 2024 - super.birthDate.getYear();
        return age;
    }    

}
