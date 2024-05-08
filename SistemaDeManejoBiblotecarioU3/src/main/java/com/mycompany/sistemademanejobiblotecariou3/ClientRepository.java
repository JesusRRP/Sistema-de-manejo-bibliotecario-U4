package com.mycompany.sistemademanejobiblotecariou3;

import java.util.ArrayList;

/**
 *
 * @author jesusrrp
 */
public class ClientRepository {

    public static ArrayList<Client> clientRepository = new ArrayList<Client>();

    public static void crud() {
        int option;

        do {
            System.out.println("CRUD de clientes:");
            System.out.println("1. Crear un cliente");
            System.out.println("2. Ver Clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Borrar un cliente");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            option = UtilitaryClass.consoleReaderInt("Que desea hacer?, (si deaseas regresar o  salir seleccione la opcion 5)");

            switch (option) {
                case 1:
                    System.out.println("Ha seleccionado Crear un cliente");
                    createClient();
                    break;
                case 2:
                    System.out.println("Ha seleccionado ver cliente");
                    readClients();
                    break;
                case 3:
                    System.out.println("Ha seleccionado atcualizar clientes");
                    readClients();
                    updateClients();
                    break;
                case 4:
                    System.out.println("Ha seleccionado borrar un cliente");
                    readClients();
                    deleteClients();
                    break;
                case 5:
                    System.out.println("Saliendo de CRUD de clientes...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (option != 5);
    }

    public static void createClient() {
        
        StringValidator isValidName = (e) -> e.matches("^[a-zA-Z]{2,30}$");
        String newName = UtilitaryClass.consoleReaderString("Ingrese el Nombre del Cliente", isValidName);
        String newLastName = UtilitaryClass.consoleReaderString("Ingrese el Apellido del Cliente", isValidName);
        
        StringValidator isValidUser = (e) -> e.matches("^[a-z0-9_-]{3,16}$");
        String newUsername = UtilitaryClass.consoleReaderString("ingresar el username del Cliente", isValidUser);
    
        StringValidator isStrongPassword = (e) -> e.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        String newPassword = UtilitaryClass.consoleReaderString("ingresar la contraseña", isStrongPassword);
        
        
        IntegerValidator isValidYear = (n) -> n >= 1900 && n <= 2024;
         IntegerValidator isValidMonth = (n) -> n >= 1 && n <= 12;
         IntegerValidator isValidDay = (n) -> n >= 1 && n <= 31;
         
        
        System.out.println("Ingrese la fecha de nacimiento del Cliente");
        int newYear = UtilitaryClass.consoleReaderInt("Año:", isValidYear);
        int newMonth = UtilitaryClass.consoleReaderInt("Mes:", isValidMonth);
        int newDate = UtilitaryClass.consoleReaderInt("Dia:", isValidDay);

        clientRepository.add(new Client(newName, newLastName, newYear, newMonth, newDate, newUsername, newPassword));
        System.out.println("Cliente creado con exito");
    }

    public static void readClients() {
        System.out.println("Todos los clientes existentes en el sistema:");

        for (int i = 0; i < clientRepository.size(); i++) {
            System.out.printf("| Nombre: %-35s  Apellido: %-35s edad:  %-3d| ", clientRepository.get(i).getName(), clientRepository.get(i).getLastName(), clientRepository.get(i).getAge());

            System.out.println(" \n  Libros prestados :  ");
            if (clientRepository.get(i).borrowedBooks.size() == 1) {
                System.out.println("1.-" + clientRepository.get(i).borrowedBooks.get(0).getTitle());
            } else if (clientRepository.get(i).borrowedBooks.size() == 2) {
                System.out.println("1.- " + clientRepository.get(i).borrowedBooks.get(0).getTitle());
                System.out.println("2.- " + clientRepository.get(i).borrowedBooks.get(1).getTitle());
            } else if (clientRepository.get(i).borrowedBooks.size() == 3) {
                System.out.println("1.-" + clientRepository.get(i).borrowedBooks.get(0).getTitle());
                System.out.println("2.-" + clientRepository.get(i).borrowedBooks.get(1).getTitle());
                System.out.println("3.-" + clientRepository.get(i).borrowedBooks.get(2).getTitle());
            }

            if (clientRepository.get(i).borrowedBooks.isEmpty()) {
                System.out.println("Este usuario no tiene libros prestados");
            }
            System.out.println("");
        }
    }

    public static void updateClients() {

        int index = UtilitaryClass.consoleReaderInt("Que cliente desea actualizar") - 1;
        System.out.println("1.-Nombre");
        System.out.println("2.-Apellido");
        System.out.println("3.-Fecha de nacimiento");

        int Option = UtilitaryClass.consoleReaderInt("Que elemento desea actualizar");

        switch (Option) {
            case 1:
                String newName = UtilitaryClass.consoleReaderString("ingresa el nuevo Nombre ");
                clientRepository.get(index).setName(newName);
                break;
            case 2:
                String newLastName = UtilitaryClass.consoleReaderString("ingresa el nuevo Apellido ");
                clientRepository.get(index).setLastName(newLastName);
                break;
            case 3:
                System.out.println("Ingrese nueva fecha de nacimiento");
                int newYear = UtilitaryClass.consoleReaderInt("año: ");
                clientRepository.get(index).birthDate.setYear(newYear);
                int newMonth = UtilitaryClass.consoleReaderInt("mes:");
                clientRepository.get(index).birthDate.setYear(newMonth);
                int newDate = UtilitaryClass.consoleReaderInt("dia:");
                clientRepository.get(index).birthDate.setYear(newDate);
                break;
            default:
                System.out.println("Opcion no encontrada");
        }
        System.out.println("Cliente actualizado correctamente");
    }

    public static void deleteClients() {
        int index = UtilitaryClass.consoleReaderInt("que cliente deseas eliminar") - 1;

        if (clientRepository.get(index).borrowedBooks.isEmpty()) {
            clientRepository.remove(index);
            System.out.println("Cliente removido exitosamente");
        } else {
            System.out.println("No se puede eliminar el cliente ya que tine libros prestados");
        }

    }

    public static void createSomeClients() {
        clientRepository.add(new Client("Juan ", "Perez", 2000, 1, 15, "client1", "123"));
        clientRepository.add(new Client("Evelyn ", "Gonzales", 1990, 2, 16, "client2", "222"));
        clientRepository.add(new Client("Pepe ", "Pecas", 1980, 3, 17, "client3",  "333"));
        clientRepository.add(new Client("Peter ", "Parker", 1970, 4, 18, "client4", "444"));
    }

    public static void readClientsWithBooks() {

        for (int i = 0; i < clientRepository.size(); i++) {
            if (clientRepository.get(i).borrowedBooks.isEmpty()) {

            } else {

                System.out.printf("| Nombre: %-35s  Apellido: %-35s edad:  %-3d| ", clientRepository.get(i).getName(), clientRepository.get(i).getLastName(), clientRepository.get(i).getAge());

                System.out.println(" \n  Libros prestados :  ");
                
                if (clientRepository.get(i).borrowedBooks.size() == 1) {
                    System.out.println("1.-" + clientRepository.get(i).borrowedBooks.get(0).getTitle());
                } else if (clientRepository.get(i).borrowedBooks.size() == 2) {
                    System.out.println("1.- " + clientRepository.get(i).borrowedBooks.get(0).getTitle());
                    System.out.println("2.- " + clientRepository.get(i).borrowedBooks.get(1).getTitle());
                } else if (clientRepository.get(i).borrowedBooks.size() == 3) {
                    System.out.println("1.-" + clientRepository.get(i).borrowedBooks.get(0).getTitle());
                    System.out.println("2.-" + clientRepository.get(i).borrowedBooks.get(1).getTitle());
                    System.out.println("3.-" + clientRepository.get(i).borrowedBooks.get(2).getTitle());
                }

                System.out.println("");
            }
        }
    }

}
