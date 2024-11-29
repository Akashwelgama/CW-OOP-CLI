package com.cruiser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public void saveConfiguration(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("Configuration/configuration.json")) {

            gson.toJson(this, writer);
            System.out.println("Configuration object saved successfully !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration retrievConfiguration(){
        Gson gson = new Gson();
        Configuration configuration = new Configuration(0, 0, 0, 0);

        try (FileReader reader = new FileReader("Configuration/configuration.json")) {

            Configuration result = gson.fromJson(reader, Configuration.class);
            configuration.setInitialTicketCount(result.getInitialTicketCount());
            configuration.setMaxTicketCapacity(result.getMaxTicketCapacity());
            configuration.setTicketReleaseRate(result.getTicketReleaseRate());
            configuration.setTicketRetrievalRate(result.getTicketRetrievalRate());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public static boolean isExisting(){

        File file = new File("Configuration/configuration.json");
        boolean flag = false;
        if (file.exists()) {

            flag = true;
            if (!(file.isFile() && file.canRead())) flag = false;



        }

        return false;
    }

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void logInfo(String string){
        logger.info(string);
    }



    public Configuration(int initialTicketCount, int ticketReleaseRate, int ticketRetrievalRate, int maxTicketCapacity) {
        this.initialTicketCount = initialTicketCount;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
