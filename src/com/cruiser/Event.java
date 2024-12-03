package com.cruiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Event implements Runnable {

    private Configuration eventDynamics;
    private TicketPool table;

    private static ArrayList<Thread> sleepers = new ArrayList<>();

    private volatile boolean terminate = false;

    public Configuration getEventDynamics() {
        return eventDynamics;
    }

    public TicketPool getTable() {
        return table;
    }


    public boolean isTerminate() {
        return terminate;
    }


    public static void addSleeper(Thread thread){
        sleepers.add(thread);
    }

    public static void removeSleeper(Thread thread){
        sleepers.remove(thread);
    }

    public static void interruptAll(){
        while(!sleepers.isEmpty()){
            Thread thread = sleepers.get(0);
            thread.interrupt();
            sleepers.remove(0);
        }
    }


    public Event(Configuration eventDynamics){
        this.eventDynamics = eventDynamics;
        table = new TicketPool(eventDynamics.getMaxTicketCapacity(), eventDynamics.getInitialTicketCount());
    }

    public void terminate(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Q to exit the program: ");
            String input = scanner.nextLine().toUpperCase();
            System.out.println(input + "***********");
            if (input.equals("Q")) {
                terminate = true;
                interruptAll();
                break;

            }
        }
    }


    @Override
    public void run() {
    terminate();

    }



}






class VendorEngine implements Runnable{

    private Event event;


    public void vendorManager(){

        while(!event.isTerminate()){

            List<Vendor> vendorSet =
                    Person.createPeople(Vendor.class, event.getEventDynamics().getTicketReleaseRate(), event);

            for(Vendor vendor: vendorSet){
                if (event.getTable().isVendorWaitingRoomFull()) continue;
                Thread customerThread = new Thread(vendor);
                customerThread.start();
            }

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Problem occurred when initiating the vendor threads");
            }
        }
    }
    @Override
    public void run() {
        vendorManager();
    }

    VendorEngine(Event event) {
        this.event = event;
    }

}






class CustomerEngine implements Runnable{

    private Event event;



    public void customerManager(){


        while(!event.isTerminate()){

            List<Customer> customerSet =
                    Person.createPeople(Customer.class, event.getEventDynamics().getTicketRetrievalRate(), event);

            for(Customer customer: customerSet){
                if (event.getTable().isCustomerWaitingRoomFull()) continue;
                Thread customerThread = new Thread(customer);
                customerThread.start();
            }

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Problem occurred when initiating the customer threads");
            }
        }
    }


    @Override
    public void run() {
        customerManager();
    }

    CustomerEngine(Event event) {
        this.event = event;
    }

}