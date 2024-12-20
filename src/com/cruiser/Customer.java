package com.cruiser;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {


    @Override
    public void action(){
        System.out.println(getName() + "is buying tickets !");
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
        if (getEvent().getTable().getCustomerWaitingCount() + 1 >= 100)
            getEvent().getTable().setCustomerWaitingRoomFull(true);

        getEvent().getTable().setCustomerWaitingCount(getEvent().getTable().getCustomerWaitingCount() + 1);

        try {
            accessCustomerWaitingRoom().await();
            getEvent().getTable().setCustomerWaitingCount(getEvent().getTable().getCustomerWaitingCount() - 1);
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

        return ((getEvent().getTable().getCurrentTicketCount() - getNumberOfTickets()) >= getEvent().getTable().getMaxTickets());
    }

    @Override
    public void waitingMessage() {
        System.out.println("Customer" + getName() + "Entered the waiting room");

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
