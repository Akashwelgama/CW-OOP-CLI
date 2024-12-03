package com.cruiser;




public class Customer extends Person {


    @Override
    public void action(){
//        System.out.println(getName() + " is buying " + getNumberOfTickets() +" tickets !");

        try{
            Event.addSleeper(Thread.currentThread());
            Thread.sleep(100);
            Event.removeSleeper(Thread.currentThread());
            ExternalConsole.logToConsole(getName() + " is buying " + getNumberOfTickets() +" tickets !");
            Configuration.logInfo(getName() + " is buying " + getNumberOfTickets() +" tickets !");
            getEvent().getTable().buyTickets(getNumberOfTickets());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + "Could not successfully buy the tickets");
            System.out.println("Error at the ticket table !");
        }

    }

    @Override
    public void waitNow(){




        try {
            Event.addSleeper(Thread.currentThread());
            accessCustomerWaitingRoom().await();
             //don't worry too much because we are using a ree...lock fair....
            Event.removeSleeper(Thread.currentThread());
            //no need to use a try finally block because we are going to remove threads in the interruptALL() method in Event class.


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
//        System.out.println(getName() + "Entered the waiting room");
        ExternalConsole.logToConsole(getName() + "Entered the waiting room");

    }

    @Override
    public void leavingMessage() {
//        System.out.println(getName() + "Leaved the waiting room");
        ExternalConsole.logToConsole(getName() + "Leaved the waiting room");
    }

    @Override
    public void changeWaitingCount(int count) {
        getEvent().getTable().setCustomerWaitingCount(getEvent().getTable().getCustomerWaitingCount() + count);

        //if (getEvent().getTable().getCustomerWaitingCount() < 100)
        getEvent().getTable().setCustomerWaitingRoomFull(getEvent().getTable().getCustomerWaitingCount() + 1 >= 100);
    }





    public Customer(String name, int numberOfTickets, Event event) {
        super(name, numberOfTickets, event);
    }

    @Override
    public void run() {
        getEvent().getTable().useTable(this);
    }
}
