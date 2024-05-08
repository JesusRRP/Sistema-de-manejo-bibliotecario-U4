package com.mycompany.sistemademanejobiblotecariou3;

import java.util.Date;

/**
 * Clase book, simplemente representa libros
 *
 * @author jesusrrp
 */
public class Book {

    private String isbn;
    private String title;
    private Author author;
    private Date publishDate;
   private Boolean isAvailable;

    public Book(String isbn, String title, Author author, int year, int month, int date) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = new Date(year, month, date);
        this.isAvailable = true; //al crear un libro en teoria debe de estar disponible porque no se ha prestado 
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }
    
    

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
}
