package com.cruiser.console;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleApplication {
    private static JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("External Console");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);

            frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
            frame.setVisible(true);
        });

        // Start a server socket to receive messages
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        String finalMessage = message;
                        SwingUtilities.invokeLater(() -> textArea.append(finalMessage + "\n"));
                    }
                } catch (IOException e) {
                    // Handle client disconnects or errors
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Handle server socket errors
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting console server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}