/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 * @author jesusrrp
 */
public class Login {

    //regresa mensaje cifrado
    public static ArrayList<User> users = new ArrayList<User>();

    public static String sha256(String mensaje) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha.digest(mensaje.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void initialize() {

        String username2 = UtilitaryClass.consoleReaderString("Username:  "); //validar para no tener dos usernames iguales
        int index = searchIndexOfUser(username2);
        if (index != -1) {
            String password2 = Login.sha256(UtilitaryClass.consoleReaderString("Password:  "));

            if (username2 == null ? users.get(index).getUsername() == null : users.get(index).equals(users.get(index).getUsername()) && password2 == null ? users.get(index).getPassword() == null : password2.equals(users.get(index).getPassword())) {
                System.out.println("Bienvenido");

                if (users.get(index) instanceof Administrator administrator) {
                    char getout = 'N';
                    do {
                        menuAdmins(administrator);
                        getout = UtilitaryClass.consoleReaderChar("Si desea salir escriba  la letra s de lo contrario escriba cualquier otra letra");
                    } while (getout != 's');
                    System.out.println("Adios...");

                } else if (users.get(index) instanceof Client client) {
                    char getout = 'N';
                    do {
                        menuClients((Client) users.get(index));
                        getout = UtilitaryClass.consoleReaderChar("Si desea salir escriba  la letra s de lo contrario escriba cualquier otra letra");
                    } while (getout != 's');
                    System.out.println("Adios...");

                }

            } else {
                System.out.println("Credenciales incorrectas");
            }
        } else {
            System.out.println("user not found");
        }

    }

    public static void menuAdmins(Administrator Admin) {
        if (!Admin.isSuperAdmin) {
            printCrudPermissions(Admin);
            selectAction();
        } else {
            printCrudPermissions(Admin);
            selectActionSuperAdmin();
        }

    }

    public static void menuClients(Client cliente) {
        System.out.println("1.- Ver libros");
        System.out.println("2.- Ver  transacciones");
        System.out.println("3.- Ver transacciones y filtrar por fecha ");
        int opcion = UtilitaryClass.consoleReaderInt("¿Que deseas hacer?");
        if (opcion == 1) {
            BookRepository.readBooksAvailable();
        } else if (opcion == 2) {
            TransactionExecution.showTransactions(cliente);
        } else if (opcion == 3) {
            TransactionExecution.showTransactionsFiltrationByDate(cliente);
        }
    }

    public static int searchIndexOfUser(String username) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                index = i;
            }
        }
        return index;
    }

    public static void printCrudPermissions(Administrator admin) {
        ArrayList<Permission> permisos = admin.permissions;

        System.out.println("Usted tiene permisos de:");

        for (int i = 0; i < permisos.size(); i++) {

            if (permisos.get(i) == Permission.DELETE) {
                System.out.println("D) delete");
            }

            if (permisos.get(i) == Permission.READ) {
                System.out.println("R) read");
            }

            if (permisos.get(i) == Permission.WRITE) {
                System.out.println("C) write ");
            }

        }
    }

    public static void selectAction() {
        CharValidator isValidOption = (c) -> c == 'R' || c == 'C' || c == 'D';
        char option = UtilitaryClass.consoleReaderChar("ingrese la opcion que deseas hacer", isValidOption);// recuerda vaidar con lambda
        selectRepository(option);
    }

    public static void selectActionSuperAdmin() {
        CharValidator isValidOption = (c) -> c == 'R' || c == 'C' || c == 'D';
        char option = UtilitaryClass.consoleReaderChar("ingrese la opcion que deseas hacer", isValidOption);// recuerda vaidar con lambda
        selectRepositorySuperAdmin(option);
    }

    public static void selectRepository(int option) {
          IntegerValidator isValidNumb = (n) -> n >= 1 && n <= 3;
        System.out.println("1.-Client Repository");
        System.out.println("2.-Book Repository");
        System.out.println("3.-Author Repository");
        int optionRepository = UtilitaryClass.consoleReaderInt("en que repositorio deseas accionar", isValidNumb); // recuerda validar con lambda

        switch (optionRepository) {
            case 1:
                chosedClientRepository(option);
                break;
            case 2:
                chosedBookRepository(option);
                break;
            case 3:
                chosedAuthorRepository(option);
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void selectRepositorySuperAdmin(int option) {
          IntegerValidator isValidNumb = (n) -> n >= 1 && n <= 4;
        System.out.println("1.-Client Repository");
        System.out.println("2.-Book Repository");
        System.out.println("3.-Author Repository");
        System.out.println("4.-AdminRepository");
        int optionRepository = UtilitaryClass.consoleReaderInt("en que repositorio deseas accionar", isValidNumb); // recuerda validar con lambda

        switch (optionRepository) {
            case 1:
                chosedClientRepository(option);
                break;
            case 2:
                chosedBookRepository(option);
                break;
            case 3:
                chosedAuthorRepository(option);
                break;

            case 4:
                chosedAdminRepository(option);
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void chosedAdminRepository(int option) {
        switch (option) {
            case 'C':
                AdministratorRepository.create();
                break;

            case 'R':
                AdministratorRepository.read();
                break;

            case 'D':
                AdministratorRepository.delete();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void chosedClientRepository(int option) {
        switch (option) {
            case 'C':
                ClientRepository.createClient();
                break;

            case 'R':
                ClientRepository.readClients();
                break;

            case 'D':
                ClientRepository.deleteClients();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void chosedBookRepository(int option) {
        switch (option) {
            case 'C':
                BookRepository.createBooks();
                break;

            case 'R':
                BookRepository.readBooks();
                break;

            case 'D':
               BookRepository.deleteBook();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void chosedAuthorRepository(int option) {
        switch (option) {
            case 'C':
                AuthorRepository.createAuthor();
                break;

            case 'R':
                AuthorRepository.readAuthors();
                break;

            case 'D':
                AuthorRepository.deleteAuthors();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void createNewUsersArray() {
        ArrayList<User> usuarios = new ArrayList<User>();
        usuarios.addAll(AdministratorRepository.administratorRepository);
        usuarios.addAll(ClientRepository.clientRepository);
        users = usuarios;
    }

}
