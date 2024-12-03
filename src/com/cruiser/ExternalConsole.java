package com.cruiser;

import java.io.*;
import java.net.Socket;

public class ExternalConsole {
    private static Socket socket;
    private static BufferedWriter writer;
    private static boolean consoleRunning = false;

    public static void startConsole() {
        if (consoleRunning) return;

        try {
            // Build the command to start the console application
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome +
                    File.separator + "bin" + File.separator + "java";
            String classpath = System.getProperty("java.class.path");
            String className = "com.cruiser.console.ConsoleApplication";

            ProcessBuilder builder = new ProcessBuilder(
                    javaBin, "-cp", classpath, className);

            // Start the console application
            Process process = builder.start();

            // Give the console application time to start
            Thread.sleep(2000);

            // Connect to the console application's server socket
            socket = new Socket("localhost", 5002);
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            consoleRunning = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void logToConsole(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            } else {
                System.err.println("Console not running. Message: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeConsole() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            consoleRunning = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}