package com.cruiser;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {


    @Override
    public void action(){
        System.out.println(getName() + " is buying " + getNumberOfTickets() +" tickets !");
        Configuration.logInfo(getName() + " is buying " + getNumberOfTickets() +" tickets !");
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + "Could not successfully buy the tickets");
            System.out.println("Error at the ticket table !");
        }
        getEvent().getTable().buyTickets(getNumberOfTickets());
    }

    @Override
    public void waitNow(){




        try {

            accessCustomerWaitingRoom().await();
             //don't worry too much because we are using a ree...lock fair....



        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Waiting room error");

        }



    }

    @Override
    public void continueNow() {
        accessVendorWaitingRoom().signalAll();

    }

    @Override
    public boolean proceed() {

        return ((getEvent().getTable().getCurrentTicketCount() - getNumberOfTickets()) >= 0);
    }

    @Override
    public void waitingMessage() {
        System.out.println(getName() + "Entered the waiting room");


    }

    @Override
    public void leavingMessage() {
        System.out.println(getName() + "Leaved the waiting room");

    }

    @Override
    public void changeWaitingCount(int count) {
        getEvent().getTable().setCustomerWaitingCount(getEvent().getTable().getCustomerWaitingCount() + count);

        //if (getEvent().getTable().getCustomerWaitingCount() < 100)
        getEvent().getTable().setCustomerWaitingRoomFull(getEvent().getTable().getCustomerWaitingCount() + 1 >= 100);
    }

    public List<Customer> customerList(){

        return Person.createPeople(Customer.class, getEvent().getEventDynamics().getTicketRetrievalRate(), getEvent());
    }

    public Customer(String name, int numberOfTickets, Event event) {
        super(name, numberOfTickets, event);
    }

    @Override
    public void run() {
        getEvent().getTable().useTable(this);
    }
}
