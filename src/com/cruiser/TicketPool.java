package com.cruiser;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {

    private ArrayList<Ticket> ticketPool = new ArrayList<>();
    private int maxTickets;

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }

    private int currentTicketCount;

    //update the constructor so the current ticket count can be updated

    private ReentrantLock lock = new ReentrantLock(true);

    private Condition customerWaitingRoom = lock.newCondition();
    private Condition vendorWaitingRoom = lock.newCondition();

    private volatile boolean customerWaitingRoomFull = false;
    private volatile boolean vendorWaitingRoomFull = false;

    private int customerWaitingCount = 0;
    private int vendorWaitingCount = 0;

    public ArrayList<Ticket> getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(ArrayList<Ticket> ticketPool) {
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
    }

    public void buyTickets(int ticketBuyingCount){
        currentTicketCount -= ticketBuyingCount;
    }



//    public void buyTickets(Customer customer){
//
//    }

    public void useTable(Person person) {
        lock.lock();
        try {
            while (!person.proceed()) {
                person.waitingMessage();
                person.waitNow();
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

    //we will see if this will work
    public TicketPool(int maxTickets, int currentTicketCount) {
        this.maxTickets = maxTickets;
        this.currentTicketCount = currentTicketCount;
    }


}
