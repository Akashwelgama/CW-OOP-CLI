package com.cruiser;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {

        int[] integer = {1, 3, 4};
//        double[] theDouble =(double[]) integer;

        int[] moreInt = integer;


//        System.out.println(Arrays.toString(splitInteger(1000)));
//
//        Random random = new Random();
//        System.out.println(random.nextInt(100));


        Class<? extends Person> Customer;
//        List<Customer> peopleList = Person.createPeople(Customer.class, 100);
//        System.out.println(peopleList);



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
