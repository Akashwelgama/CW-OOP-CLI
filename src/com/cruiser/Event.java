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



}






class VendorEngine implements Runnable{

    private Event event;


    public void vendorManager(){
        List<Vendor> vendorSet =
                Person.createPeople(Vendor.class, event.getEventDynamics().getTicketRetrievalRate(), event);
        while(!event.isTerminate()){
            for(Vendor vendor: vendorSet){
                if (!(event.getTable().isVendorWaitingRoomFull())) continue;
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
        List<Customer> customerSet =
                Person.createPeople(Customer.class, event.getEventDynamics().getTicketRetrievalRate(), event);

        while(!event.isTerminate()){
            for(Customer customer: customerSet){
                if (!(event.getTable().isCustomerWaitingRoomFull())) continue;
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