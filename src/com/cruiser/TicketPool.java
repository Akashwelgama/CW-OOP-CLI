package com.cruiser;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {

    //Remember to add the initial about of tickets of to the ticket array
    //within the event class when the program initialises.
    private ArrayList<Integer> ticketPool = new ArrayList<>();
    private int maxTickets;

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }

    public void setCurrentTicketCount(int currentTicketCount) {
        this.currentTicketCount = currentTicketCount;
    }

    private int currentTicketCount;



    private ReentrantLock lock = new ReentrantLock(true);

    private Condition customerWaitingRoom = lock.newCondition();
    private Condition vendorWaitingRoom = lock.newCondition();

    private volatile boolean customerWaitingRoomFull = false;
    private volatile boolean vendorWaitingRoomFull = false;

    private int customerWaitingCount = 0;
    private int vendorWaitingCount = 0;

    public ArrayList<Integer> getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(ArrayList<Integer> ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getCustomerWaitingCount() {
        return customerWaitingCount;
    }

    public void setCustomerWaitingCount(int customerWaitingCount) {
        this.customerWaitingCount = customerWaitingCount;
    }

    public int getVendorWaitingCount() {
        return vendorWaitingCount;
    }

    public void setVendorWaitingCount(int vendorWaitingCount) {
        this.vendorWaitingCount = vendorWaitingCount;
    }

    public boolean isCustomerWaitingRoomFull() {
        return customerWaitingRoomFull;
    }

    public void setCustomerWaitingRoomFull(boolean customerWaitingRoomFull) {
        this.customerWaitingRoomFull = customerWaitingRoomFull;
    }

    public boolean isVendorWaitingRoomFull() {
        return vendorWaitingRoomFull;
    }

    public void setVendorWaitingRoomFull(boolean vendorWaitingRoomFull) {
        this.vendorWaitingRoomFull = vendorWaitingRoomFull;
    }

    public Condition getCustomerWaitingRoom() {
        return customerWaitingRoom;
    }

    public Condition getVendorWaitingRoom() {
        return vendorWaitingRoom;
    }

    public int getMaxTickets() {
        return maxTickets;
    }


    public void sellTickets(int ticketReleaseCount){

        currentTicketCount += ticketReleaseCount;
        for (int i = 0; i < ticketReleaseCount; i++){
            ticketPool.add(1);
        }
    }

    public void buyTickets(int ticketBuyingCount){

        currentTicketCount -= ticketBuyingCount;

        int index = ticketPool.size() - 1;
        for(int i = 0; i < ticketBuyingCount; i++){
            ticketPool.remove(index);
            index--;
        }
    }



//    public void buyTickets(Customer customer){
//
//    }



    public void useTable(Person person) {
        person.changeWaitingCount(1);
        lock.lock();
        person.changeWaitingCount(-1);
        try {
            while (!person.proceed()) {
                person.waitingMessage();
                person.waitNow();
                //print the leaving message
            }
            person.action();
            person.continueNow();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("An error at the ticket table");
        } finally {
            lock.unlock();
        }

    }






    //we are making this change to make sure that this is working...
    public TicketPool(int maxTickets, int currentTicketCount) {
        this.maxTickets = maxTickets;
        this.currentTicketCount = currentTicketCount;
    }


}
