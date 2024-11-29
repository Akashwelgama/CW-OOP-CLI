package com.cruiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;


public abstract class Person implements Runnable{

    private static int customerID = 1;
    private static int vendorID = 1;

    private Event event;

    private Condition vendorWaitingRoom;
    private Condition customerWaitingRoom;


    private final String name;

    private final int numberOfTickets;

    public Event getEvent() {
        return event;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getName() {
        return name;
    }

    public abstract void action(); //Implement the ticket buying action
    public abstract void waitNow() throws InterruptedException; //Implement the waiting inside the loop

    public abstract void continueNow(); //Signalling all the threads to move to the active stage

    public abstract boolean proceed(); //Make sure weather should wait or continue

    public abstract void waitingMessage(); //Message printing when the thread is going to sleep

    public abstract void changeWaitingCount(int count);

    public abstract void leavingMessage();


    public Condition accessCustomerWaitingRoom(){
        return customerWaitingRoom;
    }


    public Condition accessVendorWaitingRoom(){
        return vendorWaitingRoom;
    }







    public static <T extends Person> List<T> createPeople(Class<T> cls, int rate, Event theEvent) {
        int[] personDynamics = splitInteger(rate);
        List<T> people = new ArrayList<>(personDynamics.length);

        for (int i = 0; i < personDynamics.length; i++) {
            try {

                T person = cls.getDeclaredConstructor(String.class, int.class, Event.class)
                        .newInstance(cls.getSimpleName() + " " + provideID(cls), personDynamics[i], theEvent);

                people.add(person);
            } catch (Exception e) {
                throw new RuntimeException("Error creating the Person: ", e);
            }
        }

        return people;
    }


    public static <T extends Person> int provideID(Class<T> cls){

        int index = 0;
        if(cls.getSimpleName().equals("Vendor")){
            index = vendorID++;
        } else if (cls.getSimpleName().equals("Customer")) {
            index = customerID++;
        }else {
            index = -1;
        }


        return index;
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

    public Person(String name, int numberOfTickets, Event event) {

        this.name = name;
        this.numberOfTickets = numberOfTickets;
        this.event = event;
        this.vendorWaitingRoom = event.getTable().getVendorWaitingRoom();
        this.customerWaitingRoom = event.getTable().getCustomerWaitingRoom();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
