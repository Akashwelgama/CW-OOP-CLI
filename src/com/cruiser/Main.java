package com.cruiser;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Configuration currentConfiguration;


    private static Event event;

    //Remember to initiate the lock here

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

            if (true) { // there is config file found
                System.out.println("""
                        Config file detected !
                        Do you want to load the saved Configuration settings?
                        Y - yes
                        N - No
                        """);

                String choice = scanner.nextLine();

                loadConfigLoop:
                while (true) {
                    if (choice.equalsIgnoreCase("N")) {
                        takeUserInput();
                        break loadConfigLoop;
                    } else if (choice.equalsIgnoreCase("Y")) {
                        //Do the logic to load the config file and save it to the
                        //currentConfig object
                        break loadConfigLoop;
                    } else {
                        System.out.println("Invalid Input!!!");
                    }
                }
            } else {
                takeUserInput();
            }

        event = new Event(currentConfiguration);
        event.getTable().sellTickets(currentConfiguration.getInitialTicketCount());
        Thread vendors = new Thread(new VendorEngine(event));
        Thread customers = new Thread(new CustomerEngine(event));

        vendors.start();
        customers.start();

    }


    public static void takeUserInput(){
        Scanner scanner = new Scanner(System.in);
        String[] messages = {
                "Enter the Initial amount of tickets in the ticket pool: ",
                "Enter the ticket release rate: ",
                "Enter the ticket retrieval rate: ",
                "Enter the max capacity of the ticket pool: "
        };

        int[] userInputs = new int[4];

        for (int i = 0; i < 4; i++) {
            validatorLoop:
            while (true) {
                try {
                    System.out.print(messages[i]);
                    int input = scanner.nextInt();
                    if (input >= 0) {
                        userInputs[i] = input;
                        break validatorLoop;
                    } else {
                        System.out.println("Please enter a positive integer !");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid Integer !");
                } finally {
                    scanner.nextLine();
                }
            }
        }

        System.out.println(Arrays.toString(userInputs));
        currentConfiguration = new Configuration(userInputs[0], userInputs[1], userInputs[2], userInputs[3]);
    }





}

