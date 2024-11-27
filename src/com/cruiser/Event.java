package com.cruiser;

import java.util.List;

public class Event implements Runnable {

    private Configuration eventDynamics;
    private TicketPool table;

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

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    public Event(Configuration eventDynamics){
        this.eventDynamics = eventDynamics;
        table = new TicketPool(eventDynamics.getMaxTicketCapacity(), eventDynamics.getInitialTicketCount());
    }


    @Override
    public void run() {


    }

    //time to implement the customer manager function


    public void engine(){

    }




}


class VendorEngine implements Runnable{

    private Event event;

    public void VendorManager(){
        List<Vendor> customerSet =
                Person.createPeople(Vendor.class, event.getEventDynamics().getTicketRetrievalRate(), event);
    }
    @Override
    public void run() {

    }
    public VendorEngine(Event event){
        this.event = event;
    }

}

class CustomerEngine implements Runnable{

    private Event event;

    public void customerManager(){
        List<Customer> customerSet =
                Person.createPeople(Customer.class, event.getEventDynamics().getTicketRetrievalRate(), event);

        while(!event.isTerminate()){

        }
    }

    public static void starter(Event event){

    }


    @Override
    public void run() {

    }

    public CustomerEngine(Event event){
        this.event = event;
    }



}