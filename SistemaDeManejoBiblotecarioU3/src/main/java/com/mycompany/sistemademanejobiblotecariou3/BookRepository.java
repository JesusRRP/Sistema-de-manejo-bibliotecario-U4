package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase contiene el CRUD de Books y funciona como repositorio
 *
 * @author jesusrrp
 */
//Todo funciona bien, solo implementa la validacion faltante en deleteBook
public class BookRepository {

    public static ArrayList<Book> booksRepository = new ArrayList<Book>();

    public static void crud() {
        int option;

        do {
            System.out.println("CRUD de libros:");
            System.out.println("1. Crear un libro");
            System.out.println("2. Ver libros");
            System.out.println("3. Actualizar libro");
            System.out.println("4. Borrar un libro");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            option = UtilitaryClass.consoleReaderInt("Que desea hacer?, (si deaseas regresar o  salir seleccione la opcion 5)");

            switch (option) {
                case 1:
                    System.out.println("Ha seleccionado Crear un libro");
                    createBooks();
                    break;
                case 2:
                    System.out.println("Ha seleccionado ver libros");
                    readBooks();
                    break;
                case 3:
                    System.out.println("Ha seleccionado atcualizar lirbo");
                    readAllBooks();
                    updateBooks();
                    break;
                case 4:
                    System.out.println("Ha seleccionado borrar un libro");
                    readAllBooks();
                    deleteBook();
                    break;
                case 5:
                    System.out.println("Saliendo de CRUD de libros...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (option != 5);

    } //CRUD de libros

    public static void createBooks() {
        //Create libros
        String titulo = UtilitaryClass.consoleReaderString("Cual es el titulo del libro");
        AuthorRepository.seeAuthors();
        int autorAGuardar = UtilitaryClass.consoleReaderInt("ingresar el autor del libro (indice de los que se encuentran disponibles)");

        StringValidator isISBN = (e) -> e.matches("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d -]*$");
        String isbn = UtilitaryClass.consoleReaderString("ingresar isbn del libro: ", isISBN);
        System.out.println("Ingresar fecha de publicacion del libro: ");
        int year = UtilitaryClass.consoleReaderInt("año: ");
        int month = UtilitaryClass.consoleReaderInt("mes: ");
        int date = UtilitaryClass.consoleReaderInt("dia: ");

        booksRepository.add(new Book(isbn, titulo, AuthorRepository.authors.get(autorAGuardar - 1), year, month, date));
        AuthorRepository.authors.get(autorAGuardar - 1).setNewBook(booksRepository.get(booksRepository.size() - 1)); //Al momento de crear un nuevo libro el atributo de libros escritos por el autor debe de actualizarse
        System.out.println("Libro creado con exito");
    } //crea libro pidiendo datos a administrador

    public static void readBooks() {
        System.out.println("1.-Mostrar todos los libros");
        System.out.println("2.-Mostrar libros prestados");
        System.out.println("3.-Mostrar libros disponibles para prestamos");
        System.out.println("4.-Salir");
        int OptionRead = UtilitaryClass.consoleReaderInt("Seleccione una opcion.");

        switch (OptionRead) {
            case 1:
                System.out.println("Todos los libros existentes en el sistema:");
                System.out.println("|Autor                                              | Titulo                                | Isbn                                 | Disponibilidad          | Fecha de piblicacion                ");
                for (int i = 0; i < booksRepository.size(); i++) {
                    System.out.printf("| %-35s %-35s  | %-35s | %-25s |  %-31b| %d/ %d/ %d ", booksRepository.get(i).getAuthor().getName(), booksRepository.get(i).getAuthor().getLastName(), booksRepository.get(i).getTitle(), booksRepository.get(i).getIsbn(), booksRepository.get(i).getIsAvailable(), booksRepository.get(i).getPublishDate().getYear(), booksRepository.get(i).getPublishDate().getMonth(), booksRepository.get(i).getPublishDate().getDate());
                    System.out.println("");
                }
                break;
            case 2:
                System.out.println("Todos los libros prestados:");
                System.out.println("|Autor                                              | Titulo                                | Isbn                                 | Disponibilidad          | Fecha de piblicacion                ");
                for (int i = 0; i < booksRepository.size(); i++) {
                    if (booksRepository.get(i).getIsAvailable() == false) {
                        System.out.printf("| %-35s %-35s  | %-35s | %-25s |  %-31b| %d/ %d/ %d ", booksRepository.get(i).getAuthor().getName(), booksRepository.get(i).getAuthor().getLastName(), booksRepository.get(i).getTitle(), booksRepository.get(i).getIsbn(), booksRepository.get(i).getIsAvailable(), booksRepository.get(i).getPublishDate().getYear(), booksRepository.get(i).getPublishDate().getMonth(), booksRepository.get(i).getPublishDate().getDate());
                        System.out.println("");
                    }
                }
                break;

            case 3:
                readBooksAvailable();
                break;

            case 4:
                System.out.println("Saliendo de leer libros...");
                break;
            default:
                System.out.println("opcion  no encontrada");
        }

    }//lee libro pidiendo datos a administrador

    public static void updateBooks() {
        int j = UtilitaryClass.consoleReaderInt("ingresa el indice del libro que deseas actualizar");
        String updateOneOrAll = UtilitaryClass.consoleReaderString("Si deseas actualizar todos los datos teclea la letra t de lo contrario teclea cualquier otra letra: ");

        if ("t".equals(updateOneOrAll) || "T".equals(updateOneOrAll)) {

            String newTitulo = UtilitaryClass.consoleReaderString("Cual es el titulo del libro");
            AuthorRepository.seeAuthors();
            int newAutorAGuardar = UtilitaryClass.consoleReaderInt("ingresar el autor del libro (indice de los que se encuentran disponibles)");
            String newIsbn = UtilitaryClass.consoleReaderString("ingresar isbn del libro: ");
            System.out.println("Ingresar fecha de publicacion del libro: ");
            int newYear = UtilitaryClass.consoleReaderInt("año: ");
            int newMonth = UtilitaryClass.consoleReaderInt("mes: ");
            int newDate = UtilitaryClass.consoleReaderInt("dia: ");

            Book newBook = new Book(newIsbn, newTitulo, AuthorRepository.authors.get(newAutorAGuardar - 1), newYear, newMonth, newDate);
            booksRepository.set(j - 1, newBook);
            AuthorRepository.authors.get(newAutorAGuardar - 1).setNewBook(booksRepository.get(j - 1));

        } else {
            System.out.println("1.-Titulo");
            System.out.println("2.-Autor");
            System.out.println("3.-Isbn");
            System.out.println("4.-Fecha de publicación");
            System.out.println("5.-Salir");

            int k = UtilitaryClass.consoleReaderInt("Que elemento desea actualizar");

            switch (k) {
                case 1 -> {
                    String newTitulo = UtilitaryClass.consoleReaderString("Cual es el titulo del libro");
                    booksRepository.get(j - 1).setTitle(newTitulo);
                    System.out.println("titulo actualizado con exito.");
                }
                case 2 -> {
                    AuthorRepository.seeAuthors();

                    AuthorRepository.authors.get(AuthorRepository.getIndexOfAuthor(booksRepository.get(j - 1).getAuthor().name)).removeBook(AuthorRepository.authors.get(AuthorRepository.getIndexOfAuthor(booksRepository.get(j - 1).getAuthor().name)).getIndexOfBook(booksRepository.get(j - 1).getIsbn()));
                    //esta linea de codigo borra el libro del autor anterior 

                    int newAutorAGuardar = UtilitaryClass.consoleReaderInt("ingresar el autor del libro (indice de los que se encuentran disponibles)");
                    booksRepository.get(j - 1).setAuthor(AuthorRepository.authors.get(newAutorAGuardar - 1));

                    AuthorRepository.authors.get(newAutorAGuardar - 1).setNewBook(booksRepository.get(j - 1));// actualiza los libros de el autor elegido

                    System.out.println("autor actualizado con exito.");
                }

                case 3 -> {
                    String newIsbn = UtilitaryClass.consoleReaderString("ingresar isbn del libro: ");
                    booksRepository.get(j - 1).setIsbn(newIsbn);
                    System.out.println("isbn actualizado con exito.");
                }

                case 4 -> {
                    System.out.println("Ingresar fecha de publicacion del libro: ");
                    int newYear = UtilitaryClass.consoleReaderInt("año: ");
                    int newMonth = UtilitaryClass.consoleReaderInt("mes: ");
                    int newDate = UtilitaryClass.consoleReaderInt("dia: ");
                    Date newpublishDate = new Date(newYear, newMonth, newDate);
                    booksRepository.get(j - 1).setPublishDate(newpublishDate);
                    System.out.println("isbn actualizado con exito");
                }

                case 5 -> {
                    System.out.println("Okey, cancelando actualización... ");
                }

                default -> {
                    System.out.println("opcion no encontrada");

                }
            }
        }
    }// actualizar libros

    public static void deleteBook() {
        int index = UtilitaryClass.consoleReaderInt("Cual libro deseas eliminar") - 1;

        if (booksRepository.get(index).getIsAvailable() == true) {

            //   for (int i = 0; i < AuthorRepository.authors.size(); i++) {  //busca y borra el libro en los libros del autor //verificar luego esta validacion
            //     for (int j = 0; j < AuthorRepository.authors.get(i).books.size(); j++) {
            //     if (AuthorRepository.authors.get(i).books.get(j).getTitle() == booksRepository.get(index - 1).getTitle()) {
            //      AuthorRepository.authors.get(i).books.remove(j);
            //  System.out.println("libro borrado del autor correctamente...");
            //}
            //}
            //}
            booksRepository.remove(index);
            System.out.println("libro borrado correctamente");

            //remover libro de lista libro de autor
        } else {
            System.out.println("No se es posible eliminar el libro, ya que esta prestado");
        }

    }// borrar libros

    public static void readAllBooks() {
        System.out.println("Todos los libros existentes en el sistema:");
        System.out.println("|Autor                                              | Titulo                                | Isbn                                 | Disponibilidad          | Fecha de piblicacion                ");
        for (int i = 0; i < booksRepository.size(); i++) {
            System.out.printf("| %-35s %-35s  | %-35s | %-25s |  %-31b| %d/ %d/ %d ", booksRepository.get(i).getAuthor().getName(), booksRepository.get(i).getAuthor().getLastName(), booksRepository.get(i).getTitle(), booksRepository.get(i).getIsbn(), booksRepository.get(i).getIsAvailable(), booksRepository.get(i).getPublishDate().getYear(), booksRepository.get(i).getPublishDate().getMonth(), booksRepository.get(i).getPublishDate().getDate());
            System.out.println("");
        }
    }

    public static void readBooksAvailable() {
        System.out.println("Todos los libros diponibles para ser prestados:");
        for (int i = 0; i < booksRepository.size(); i++) {
            if (booksRepository.get(i).getIsAvailable() == true) {
                System.out.printf("| %-35s %-35s  | %-35s | %-25s |  %-31b| %d/ %d/ %d ", booksRepository.get(i).getAuthor().getName(), booksRepository.get(i).getAuthor().getLastName(), booksRepository.get(i).getTitle(), booksRepository.get(i).getIsbn(), booksRepository.get(i).getIsAvailable(), booksRepository.get(i).getPublishDate().getYear(), booksRepository.get(i).getPublishDate().getMonth(), booksRepository.get(i).getPublishDate().getDate());
                System.out.println("");
            }
        }
    }// libros disponibles para prestamos

    public static void readBooksBorrowed() {
        System.out.println("Todos los libros prestados:");
        System.out.println("|Autor                                              | Titulo                                | Isbn                                 | Disponibilidad          | Fecha de piblicacion                ");
        for (int i = 0; i < booksRepository.size(); i++) {
            if (booksRepository.get(i).getIsAvailable() == false) {
                System.out.printf("| %-35s %-35s  | %-35s | %-25s |  %-31b| %d/ %d/ %d ", booksRepository.get(i).getAuthor().getName(), booksRepository.get(i).getAuthor().getLastName(), booksRepository.get(i).getTitle(), booksRepository.get(i).getIsbn(), booksRepository.get(i).getIsAvailable(), booksRepository.get(i).getPublishDate().getYear(), booksRepository.get(i).getPublishDate().getMonth(), booksRepository.get(i).getPublishDate().getDate());
                System.out.println("");
            }
        }
    }

    public static void createSomeBooks() {
        booksRepository.add(new Book("123-12-1234-856-7", "Title1", AuthorRepository.authors.get(0), 2000, 11, 12));
        booksRepository.add(new Book("123-12-1234-856-6", "Title2", AuthorRepository.authors.get(1), 2001, 12, 1));
        booksRepository.add(new Book("123-12-1234-856-5", "Title3", AuthorRepository.authors.get(2), 2002, 13, 2));
        booksRepository.add(new Book("123-12-1234-856-4", "Title4", AuthorRepository.authors.get(0), 2003, 14, 3));
        booksRepository.add(new Book("123-12-1234-856-3", "Title5", AuthorRepository.authors.get(1), 2004, 15, 4));
    }
}
