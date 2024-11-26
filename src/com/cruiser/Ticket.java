package com.cruiser;

public class Ticket {

    private static int counter = 0;

    private final int refNo;

    public int getRefNo() {
        return refNo;
    }

    public Ticket() {
        this.refNo = counter++;
    }
}
