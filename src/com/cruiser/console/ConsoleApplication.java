package com.cruiser.console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleApplication {
    private static JTextArea textArea;
    private static ServerSocket serverSocket;

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

            // Add a listener to handle cleanup when the window is closed
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    cleanupResources(); // Clean up resources when the window is closed
                }
            });
        });

        try {
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true); // Allow port reuse
            serverSocket.bind(new java.net.InetSocketAddress(5002)); // Bind to the specified port

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
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting console server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    // Method to clean up resources (server socket, etc.)
    private static void cleanupResources() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}