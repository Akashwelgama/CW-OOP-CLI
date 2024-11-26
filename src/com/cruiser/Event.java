package com.cruiser;

import java.util.List;

public class Event implements Runnable {

    private Configuration eventDynamics;
    private TicketPool table;



    public Configuration getEventDynamics() {
        return eventDynamics;
    }

    public TicketPool getTable() {
        return table;
    }





    public Event(Configuration eventDynamics){
        this.eventDynamics = eventDynamics;
        table = new TicketPool(eventDynamics.getMaxTicketCapacity(), eventDynamics.getInitialTicketCount());
    }


    @Override
    public void run() {


    }

    //time to implement the customer manager function
    public void customerManager(){
        List<Customer> customerSet = Person.createPeople(Customer.class, eventDynamics.getTicketRetrievalRate(), this);
    }






}
