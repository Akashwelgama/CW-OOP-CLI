package com.cruiser;

public class Configuration {
    private int initialTicketCount;
    private int ticketReleaseRate;
    private int ticketRetrievalRate;
    private int maxTicketCapacity;

    public int getInitialTicketCount() {
        return initialTicketCount;
    }

    public void setInitialTicketCount(int initialTicketCount) {
        this.initialTicketCount = initialTicketCount;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTicketRetrievalRate() {
        return ticketRetrievalRate;
    }

    public void setTicketRetrievalRate(int ticketRetrievalRate) {
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Configuration(int initialTicketCount, int ticketReleaseRate, int ticketRetrievalRate, int maxTicketCapacity) {
        this.initialTicketCount = initialTicketCount;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
