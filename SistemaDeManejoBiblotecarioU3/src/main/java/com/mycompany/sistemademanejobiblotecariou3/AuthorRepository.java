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
public class AuthorRepository {

    public static ArrayList<Author> authors;

    public static void createSomeAuthors() {
        authors = new ArrayList<>();
        authors.add(new Author("Juan", "Lopez", 2000, 11, 28));
        authors.add(new Author("Leucipo", "Juarez", 2001, 8, 23));
        authors.add(new Author("Tales", "De Mileto", 1998, 3, 18));
    }//metodo para crear algunos autores inicialmente

    public static void seeAuthors() {
        System.out.println("Autores disponibles");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println((i + 1) + ".- Nombre del  autor: " + authors.get(i).name + "," + "  Apellido del autor: " + authors.get(i).lastName);
        }
    }//Metodo que se utiliza en el repositorio de books

    public static int getIndexOfAuthor(String name) {
        int index = 0;
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getName() == name) {
                index = i;
            }
        }
        return index;
    }//Metodo que se utiliza en el repositorio de books

    public static void createAuthor() {
        String newName = UtilitaryClass.consoleReaderString("Ingrese el Nombre del autor");
        String newLastName = UtilitaryClass.consoleReaderString("Ingrese el Apellido del autor");
        System.out.println("Ingrese la fecha de nacimiento del autor");
        int newYear = UtilitaryClass.consoleReaderInt("Año:");
        int newMonth = UtilitaryClass.consoleReaderInt("Mes:");
        int newDate = UtilitaryClass.consoleReaderInt("Dia:");

        authors.add(new Author(newName, newLastName, newYear, newMonth, newDate));

    }

    public static void readAuthors() {
        System.out.println("Todos los autores existentes en el sistema:");

        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("| Nombre: %-35s  Apellido: %-35s edad:  %-3d| ", authors.get(i).getName(), authors.get(i).getLastName(), authors.get(i).getAge());

            System.out.println(" \n Libros escritos :  ");
            for(int j = 0; j<authors.get(i).books.size(); j++){
                System.out.println(authors.get(i).books.get(j).getTitle());
            }

            if (authors.get(i).books.isEmpty()) {
                System.out.println("Este autor no tiene libros escritos");
            }
            System.out.println("");
        }
    }

    public static void updateAuthors() {
        int index = UtilitaryClass.consoleReaderInt("Que autor desea actualizar") - 1;
        System.out.println("1.-Nombre");
        System.out.println("2.-Apellido");
        System.out.println("3.-Fecha de nacimiento");

        int Option = UtilitaryClass.consoleReaderInt("Que elemento desea actualizar");

        switch (Option) {
            case 1:
                String newName = UtilitaryClass.consoleReaderString("ingresa el nuevo Nombre ");
                authors.get(index).setName(newName);
                break;
            case 2:
                String newLastName = UtilitaryClass.consoleReaderString("ingresa el nuevo Apellido ");
                authors.get(index).setLastName(newLastName);
                break;
            case 3:
                System.out.println("Ingrese nueva fecha de nacimiento");
                int newYear = UtilitaryClass.consoleReaderInt("año: ");
                authors.get(index).birthDate.setYear(newYear);
                int newMonth = UtilitaryClass.consoleReaderInt("mes:");
                authors.get(index).birthDate.setYear(newMonth);
                int newDate = UtilitaryClass.consoleReaderInt("dia:");
                authors.get(index).birthDate.setYear(newDate);
                break;
            default:
                System.out.println("Opcion no encontrada");
        }
        System.out.println("autor actualizado correctamente");
    }

    public static void deleteAuthors() {
        int index = UtilitaryClass.consoleReaderInt("que autor deseas eliminar") - 1;

        if (authors.get(index).books.isEmpty()) {
            authors.remove(index);
            System.out.println("Autor removido exitosamente");
        } else {
            System.out.println("No se puede eliminar el autor ya que tine libros escritos");
        }
    }

}
