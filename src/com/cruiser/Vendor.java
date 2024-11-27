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

        if (getEvent().getTable().getVendorWaitingCount() + 1 >= 100)
            getEvent().getTable().setVendorWaitingRoomFull(true);


        changeWaitingCount(1);

        try{
            accessVendorWaitingRoom().await();
            changeWaitingCount(-1);  //don't worry too much because we are using a ree...lock fair....
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

        return ((getEvent().getTable().getCurrentTicketCount() + getNumberOfTickets()) <= 0);
    }

    @Override
    public void waitingMessage() {
        System.out.println("Vendor" + getName() + "Entered the waiting room");
    }

    @Override
    public void changeWaitingCount(int count) {
        getEvent().getTable().setVendorWaitingCount(getEvent().getTable().getVendorWaitingCount() + count);
    }

    public List<Vendor> customerList(){

        return Person.createPeople(Vendor.class, getEvent().getEventDynamics().getTicketRetrievalRate(), getEvent());
    }



    public Vendor(String name, int numberOfTickets, Event event) {
        super(name, numberOfTickets, event);
    }


    @Override
    public void run() {
        getEvent().getTable().useTable(this);
    }
}
