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
public class AdministratorRepository {

    public static ArrayList<Administrator> administratorRepository = new ArrayList<Administrator>();

    public static void crud() {
        //create HashMap crud
        System.out.println("menu");
    }

    public static void create() {

        String name, lastName, password, username;
        int year, month, date;
         StringValidator isValidName = (e) -> e.matches("^[a-zA-Z]{2,30}$");
        name = UtilitaryClass.consoleReaderString("ingrese el nombre del usuario: ", isValidName);
        lastName = UtilitaryClass.consoleReaderString("ingrese apellido del usuario: ", isValidName);
      
        System.out.println("Ingrese la fecha: ");
        IntegerValidator isValidYear = (n) -> n >= 1900 && n <= 2024;
         IntegerValidator isValidMonth = (n) -> n >= 1 && n <= 12;
         IntegerValidator isValidDay = (n) -> n >= 1 && n <= 31;
         
        year = UtilitaryClass.consoleReaderInt("Año: ", isValidYear);
        month = UtilitaryClass.consoleReaderInt("mes", isValidMonth);
        date = UtilitaryClass.consoleReaderInt("dia", isValidDay);
        
        //exige que el nombre de usuario tenga entre 3 y 16 caracteres 
        //y que solo contenga letras minúsculas, números, guiones bajos y guiones medios
        
        StringValidator isValidUser = (e) -> e.matches("^[a-z0-9_-]{3,16}$");
        username = UtilitaryClass.consoleReaderString("Crea usuario nuevo", isValidUser);
        
        StringValidator isStrongPassword = (e) -> e.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        password = UtilitaryClass.consoleReaderString("Crea contraseña nueva", isStrongPassword);

        System.out.println("1.-Read");
        System.out.println("2.-Write");
        System.out.println("3.-Delete");
        System.out.println("4.-All"); //validar para que no pueda meter otro valor el usuario
         
        IntegerValidator isValidPermission = (n) -> n >= 1 && n <= 4;
         
        int permisos = UtilitaryClass.consoleReaderInt("Que permisos deseas que tenga el nuevo admin", isValidPermission);       
        ArrayList<Permission> permissions = new ArrayList<>();
        permissions = selectPermission(permisos);


        Administrator admin = new Administrator(name, lastName, year, month, date, username, password, permissions);
        administratorRepository.add(admin);
        System.out.println("Administrador creado correctamente...");
    }
    
    public static ArrayList <Permission> selectPermission(int permisos){
        ArrayList<Permission> permissions = new ArrayList<Permission>();
         Permission permisoRead = Permission.READ;
        Permission permissionDelete = Permission.DELETE;
        Permission permissionWrite = Permission.WRITE;
                switch (permisos) {
            case 1:
                permissions.add(permisoRead);
                break;
            case 2:
                permissions.add(permissionWrite);
                break;
                
            case 3 :
                permissions.add(permissionDelete);
                break;
                
            case 4:
                 permissions.add(permisoRead);
                 permissions.add(permissionWrite);
                 permissions.add(permissionDelete);
                 break;
                 
            default:
                System.out.println("permiso no encontrado");
        }
        return  permissions;
    }

    public static void read() {
        for (int i = 0; i < administratorRepository.size(); i++) {
            System.out.printf("| Nombre: %-35s  Apellido: %-35s edad:  %-3d Usuario: %-25s IsSuperAdmin: %-6s| ", administratorRepository.get(i).getName(), administratorRepository.get(i).getLastName(), administratorRepository.get(i).getAge(), administratorRepository.get(i).getUsername(), administratorRepository.get(i).isIsSuperAdmin());
            administratorRepository.get(i).printPermissions();
            
        }
    }

    public static void update() {
        System.out.println("");
        int index = UtilitaryClass.consoleReaderInt("Que Administrador  desea actualizar") - 1;
        System.out.println("1.-Nombre");
        System.out.println("2.-Apellido");
        System.out.println("3.-Fecha de nacimiento");
        System.out.println("4.-Username");
        System.out.println("5.-Password");

        int Option = UtilitaryClass.consoleReaderInt("Que elemento desea actualizar");

        switch (Option) {
            case 1:
                String newName = UtilitaryClass.consoleReaderString("ingresa el nuevo Nombre ");
                administratorRepository.get(index).setName(newName);
                break;
            case 2:
                String newLastName = UtilitaryClass.consoleReaderString("ingresa el nuevo Apellido ");
                administratorRepository.get(index).setLastName(newLastName);
                break;
            case 3:
                System.out.println("Ingrese nueva fecha de nacimiento");
                int newYear = UtilitaryClass.consoleReaderInt("año: ");
                administratorRepository.get(index).birthDate.setYear(newYear);
                int newMonth = UtilitaryClass.consoleReaderInt("mes:");
                administratorRepository.get(index).birthDate.setYear(newMonth);
                int newDate = UtilitaryClass.consoleReaderInt("dia:");
                administratorRepository.get(index).birthDate.setYear(newDate);
                break;

            case 4:
                String newUsername = UtilitaryClass.consoleReaderString("ingresa el nuevo username ");
                administratorRepository.get(index).setUsername(newUsername);
                break;

            case 5:
                String newPassword = UtilitaryClass.consoleReaderString("ingresa la nuevo contraseña ");
                administratorRepository.get(index).setUsername(newPassword);
                break;

            default:
                System.out.println("Opcion no encontrada");
        }
        System.out.println("Cliente actualizado correctamente");
    }

    public static void delete() {
        System.out.println("");
        int index = UtilitaryClass.consoleReaderInt("Que administrador deseas borrar");
        if (administratorRepository.get(index - 1).isSuperAdmin) {
            System.out.println("No se puede eliminar el superadmin");
        } else {
            administratorRepository.remove(index - 1);
            System.out.println("Administrador borrado con exito");
        }
    }

    public static void createSomeAdmins() {
        Administrator admin1 = new Administrator("superAdmin", "Perez", 2000, 11, 12, "superadmin", "password", true, selectPermission(4));
        Administrator admin2 = new Administrator("admin2", "chavez", 2000, 11, 12, "admin2", "123", selectPermission(3));
        Administrator admin3 = new Administrator("admin3", "arreola", 2001, 10, 14, "admin3", "321", selectPermission(1));
        administratorRepository.add(admin1);
        administratorRepository.add(admin2);
        administratorRepository.add(admin3);
    }
}
