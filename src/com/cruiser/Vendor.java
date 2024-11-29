package com.cruiser;

import java.util.List;

public class Vendor extends Person{


    @Override
    public void action() {
        System.out.println(getName() + "is releasing tickets !");
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + "Could not successfully release the tickets");
            System.out.println("Error at the ticket table !");
        }
        getEvent().getTable().sellTickets(getNumberOfTickets());

    }

    @Override
    public void waitNow() {






        try{
            accessVendorWaitingRoom().await();
             //don't worry too much because we are using a ree...lock fair....



        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Problem in vendor waiting room !!!");
        }



    }

    @Override
    public void continueNow() {
        accessCustomerWaitingRoom().signalAll();
    }

    @Override
    public boolean proceed() {

        return ((getEvent().getTable().getCurrentTicketCount() + getNumberOfTickets()) <= getEvent().getTable().getMaxTickets());
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
        getEvent().getTable().setVendorWaitingCount(getEvent().getTable().getVendorWaitingCount() + count);
        // if (getEvent().getTable().getVendorWaitingCount() + 1 < 100)
        getEvent().getTable().setVendorWaitingRoomFull(getEvent().getTable().getVendorWaitingCount() + 1 >= 100);
    }



    public List<Vendor> VendorList(){

        return Person.createPeople(Vendor.class, getEvent().getEventDynamics().getTicketReleaseRate(), getEvent());
    }



    public Vendor(String name, int numberOfTickets, Event event) {
        super(name, numberOfTickets, event);
    }


    @Override
    public void run() {
        getEvent().getTable().useTable(this);
    }
}
