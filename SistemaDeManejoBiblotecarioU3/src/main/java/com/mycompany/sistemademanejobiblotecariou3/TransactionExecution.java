/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jesusrrp
 */
public class TransactionExecution {

    public static ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();

    public static void bookOutings() {
        BookRepository.readAllBooks();
        int bookIndex = UtilitaryClass.consoleReaderInt("Que libros  va a ser prestado?") - 1;
        if (BookRepository.booksRepository.get(bookIndex).getIsAvailable()) {
            BookRepository.booksRepository.get(bookIndex).setIsAvailable(Boolean.FALSE);
            System.out.println("Libro disponible");
            ClientRepository.readClients();
            int clientIndex = UtilitaryClass.consoleReaderInt("A que cliente se le prestara") - 1;

            if (ClientRepository.clientRepository.get(clientIndex).borrowedBooks.size() < 3) {

                ClientRepository.clientRepository.get(clientIndex).borrowedBooks.add(BookRepository.booksRepository.get(bookIndex));
                System.out.println("El libro ha sido prestado correctamente");
                System.out.println("Por cuestiones de control ingresa la fecha de el dia de hoy");
                int month = UtilitaryClass.consoleReaderInt("Mes");
                int date = UtilitaryClass.consoleReaderInt("Dia");
                int hrs = UtilitaryClass.consoleReaderInt("Hora");
                int min = UtilitaryClass.consoleReaderInt("Minuto");

                allTransactions.add(new Transaction("borrow", ClientRepository.clientRepository.get(clientIndex), BookRepository.booksRepository.get(bookIndex), new Date(2024, month, date, hrs, min)));
                System.out.println("Transaccion guardada correctamente");
            } else {
                System.out.println("No se puede prestar mas de tres libros");
            }

        } else {
            System.out.println("El libro no puede ser prestado porque no esta disponible en este momento");
        }

    }

    public static void bookReturns() {
        ClientRepository.readClientsWithBooks();
        int indexClient = UtilitaryClass.consoleReaderInt("Que cliente regreso el libro") - 1;
        int indexBook = UtilitaryClass.consoleReaderInt("Cual libro") - 1;
        int IndexToSetBook = 0;

        String isbnToFind = ClientRepository.clientRepository.get(indexClient).borrowedBooks.get(indexBook).getIsbn();
        for (int i = 0; i < BookRepository.booksRepository.size(); i++) {
            if (BookRepository.booksRepository.get(i).getIsbn() == isbnToFind) {
                IndexToSetBook = i - 1;
                BookRepository.booksRepository.get(i).setIsAvailable(true);  // indica que el libro a regresado y ahora esta disponible
                System.out.println("libro ahora disponible");
            }
        }

        ClientRepository.clientRepository.get(indexClient).borrowedBooks.remove(indexBook); // quita el libro del cliente
        System.out.println("libro regresado correctamente");

        System.out.println("Por cuestiones de control ingresa la fecha de el dia de hoy");
        int month = UtilitaryClass.consoleReaderInt("Mes");
        int date = UtilitaryClass.consoleReaderInt("Dia");
        int hrs = UtilitaryClass.consoleReaderInt("Hora");
        int min = UtilitaryClass.consoleReaderInt("Minuto");

        allTransactions.add(new Transaction("return", ClientRepository.clientRepository.get(indexClient), BookRepository.booksRepository.get(IndexToSetBook), new Date(2024, month, date, hrs, min)));
        System.out.println("Transaccion guardada");
    }

    //esto solo es para visualizar el funcionamiento
    public static void createSomeFakeTransactions() {
        Date fecha = new Date(2023, 10, 10, 10, 30);
        Date fecha2 = new Date(2024, 10, 10, 10, 30);
        allTransactions.add(new Transaction("borrow", ClientRepository.clientRepository.get(0), BookRepository.booksRepository.get(0), fecha));
        allTransactions.add(new Transaction("borrow", ClientRepository.clientRepository.get(0), BookRepository.booksRepository.get(1), fecha));
        allTransactions.add(new Transaction("return", ClientRepository.clientRepository.get(0), BookRepository.booksRepository.get(0), fecha2));
        allTransactions.add(new Transaction("return", ClientRepository.clientRepository.get(0), BookRepository.booksRepository.get(1), fecha2));

    }

    public static void showTransactions(Client cliente) {
        System.out.println("Tus transacciones: ");
        for (int i = 0; i < allTransactions.size(); i++) {
            if (allTransactions.get(i).client == cliente) {
                System.out.printf("tipo: %-10s libro: %-10s  fecha: %-2d/%-4d/%-2d", allTransactions.get(i).type, allTransactions.get(i).book.getTitle(), allTransactions.get(i).date.getDay(), allTransactions.get(i).date.getYear(), allTransactions.get(i).date.getMonth());
                System.out.println("");
            }
        }
    }

    public static void showTransactionsFiltrationByDate(Client cliente) {
        int year = UtilitaryClass.consoleReaderInt("En que año deseas filtrar la fecha");
        int month = UtilitaryClass.consoleReaderInt("En que mes deseas filtrar la fecha");
        for (int i = 0; i < allTransactions.size(); i++) {
            if (allTransactions.get(i).client == cliente && allTransactions.get(i).date.getYear() == year && allTransactions.get(i).date.getMonth() == month) {
                System.out.printf("tipo: %-10s libro: %-10s  fecha: %-2d/%-4d/%-2d", allTransactions.get(i).type, allTransactions.get(i).book.getTitle(), allTransactions.get(i).date.getDay(), allTransactions.get(i).date.getYear(), allTransactions.get(i).date.getMonth());
                System.out.println("");
            }
        }
    }
}
