package com.aytomonzon.librocontroller1;

import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextArea textArea;

    @FXML private TextField puesto1;
    @FXML private TextField puesto2;
    @FXML private TextField puesto3;
    @FXML private TextField puesto4;
    @FXML private TextField puesto5;
    @FXML private TextField puesto6;
    @FXML private TextField puesto7;
    @FXML private TextField puesto8;

    private static final int MAX_LINE_LENGTH = 60; // Ajusta según el ancho del TextArea

    private Thread multicastThread;

    @FXML
    public void initialize() {
        multicastThread = new Thread(() -> {
            try (java.net.MulticastSocket socket = new java.net.MulticastSocket(4446)) {
                java.net.InetAddress group = java.net.InetAddress.getByName("230.0.0.1");
                socket.joinGroup(group);
                byte[] buf = new byte[1024];
                while (!Thread.currentThread().isInterrupted()) {
                    java.net.DatagramPacket packet = new java.net.DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    javafx.application.Platform.runLater(() -> {
                        appendMultiline(received);
                    });
                }
                socket.leaveGroup(group);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        multicastThread.setDaemon(true);
        multicastThread.start();

        // Cargar puestos guardados
        Preferences prefs = Preferences.userNodeForPackage(PrimaryController.class);
        puesto1.setText(prefs.get("puesto1", ""));
        puesto2.setText(prefs.get("puesto2", ""));
        puesto3.setText(prefs.get("puesto3", ""));
        puesto4.setText(prefs.get("puesto4", ""));
        puesto5.setText(prefs.get("puesto5", ""));
        puesto6.setText(prefs.get("puesto6", ""));
        puesto7.setText(prefs.get("puesto7", ""));
        puesto8.setText(prefs.get("puesto8", ""));
    }

    private void appendMultiline(String text) {
        StringBuilder sb = new StringBuilder();
        if (text.startsWith("-")) {
            int start = 0;
            while (start < text.length()) {
                int end = Math.min(start + MAX_LINE_LENGTH, text.length());
                sb.append(text, start, end).append("\n");
                start = end;
            }
        } else {
            sb.append(text).append("\n");
        }
        // Insertar al principio del TextArea
        String current = textArea.getText();
        textArea.setText(sb.toString() + current);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void salir() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void guardarPuestos() {
        Preferences prefs = Preferences.userNodeForPackage(PrimaryController.class);
        prefs.put("puesto1", puesto1.getText());
        prefs.put("puesto2", puesto2.getText());
        prefs.put("puesto3", puesto3.getText());
        prefs.put("puesto4", puesto4.getText());
        prefs.put("puesto5", puesto5.getText());
        prefs.put("puesto6", puesto6.getText());
        prefs.put("puesto7", puesto7.getText());
        prefs.put("puesto8", puesto8.getText());
    }
}
