/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemademanejobiblotecariou3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jesusrrp
 */
public class UtilitaryClass {

    public static int consoleReaderInt(String message) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(message);
        int variable = entrada.nextInt();
        return variable;
    }

    public static int consoleReaderInt(String message, IntegerValidator validator) {

        int myInt;
        Scanner myInput = new Scanner(System.in);

        while (true) {
            System.out.println(message + " :");
            myInt = myInput.nextInt();
            boolean isValid = validator.validate(myInt);

            if (isValid) {
                return myInt;
            } else {
                System.out.println("incorrecto, intente de nuevo");
            }

        }
    }

    public static String consoleReaderString(String message) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(message);
        String variable = entrada.nextLine();
        return variable;
    }

    public static String consoleReaderString(String message, StringValidator validator) {
        String myString;
        Scanner myInput = new Scanner(System.in);

        while (true) {
            System.out.println(message + " :");
            myString = myInput.nextLine();
            boolean isValid = validator.validate(myString);

            if (isValid) {
                return myString;
            } else {
                System.out.println("incorrecto, intente de nuevo");
            }

        }
    }

    public static char consoleReaderChar(String message) {
        Scanner entrada = new Scanner(System.in);
        System.out.println(message);
        char variable = entrada.next().charAt(0);
        return variable;
    }

    public static char consoleReaderChar(String message, CharValidator validator) {

        char mychar;
        Scanner myInput = new Scanner(System.in);

        while (true) {
            System.out.println(message + " :");
            mychar = myInput.next().charAt(0);
            boolean isValid = validator.validate(mychar);

            if (isValid) {
                return mychar;
            } else {
                System.out.println("incorrecto, intente de nuevo");
            }
        }
    }

    public static String generateRandomId() { // Metodo para generar unic randoms id (lo uso en la clase transaction)
        int length = 8;

        String chars = "0123456789";

        Random random = new Random();

        StringBuilder idBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            idBuilder.append(chars.charAt(index));
        }

        return idBuilder.toString();
    }

}
