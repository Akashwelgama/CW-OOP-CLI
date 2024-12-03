package com.cruiser;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

import java.util.List;
import java.util.Random;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        int[] integer = {1, 3, 4};
//        double[] theDouble =(double[]) integer;

        int[] moreInt = integer;

        Gson gson = new Gson();
//        String json = gson.toJson("Hello, Gson!");
//        System.out.println(json); // Output: "Hello, Gson!"
//        System.out.println(Arrays.toString(splitInteger(1000)));
//
//        Random random = new Random();
//        System.out.println(random.nextInt(100));


        Class<? extends Person> Customer;
//        List<Customer> peopleList = Person.createPeople(Customer.class, 100);
//        System.out.println(peopleList);


        // Log messages at different levels
//        logger.info("Application started.");
//        logger.debug("This is a debug message.");
//        logger.warn("This is a warning.");
//        logger.error("An error occurred.");
//        logger.fatal("Fatal error!");



//        ExternalConsole.closeConsole();
        // Start the custom console
        ExternalConsole.startConsole();

        // Log some messages to the custom console
        ExternalConsole.logToConsole("Hello, this is the custom console!");
//        ExternalConsole.logToConsole("You can log messages here just like a regular console.");




//        CMD.initializeCmd();
//        CMD.logCMD("BLa bla blaa bla blaaa");
    }

    public static int[] splitInteger(int total) {
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        int[] result = new int[length];



        for (int i = 0; i < total; i++) {
            int index = random.nextInt(length);
            result[index] += 1;
        }

        return result;
    }







}
