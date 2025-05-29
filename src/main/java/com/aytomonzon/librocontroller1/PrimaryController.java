package com.aytomonzon.librocontroller1;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML private Label card1;
    @FXML private Label card2;
    @FXML private Label card3;
    @FXML private Label card4;
    @FXML private Label card5;
    @FXML private Label card6;
    @FXML private Label card7;
    @FXML private Label card8;

    @FXML private Label ip1;
    @FXML private Label nombre1;
    @FXML private Label version1;
    @FXML private Label ip2;
    @FXML private Label nombre2;
    @FXML private Label version2;
    @FXML private Label ip3;
    @FXML private Label nombre3;
    @FXML private Label version3;
    @FXML private Label ip4;
    @FXML private Label nombre4;
    @FXML private Label version4;
    @FXML private Label ip5;
    @FXML private Label nombre5;
    @FXML private Label version5;
    @FXML private Label ip6;
    @FXML private Label nombre6;
    @FXML private Label version6;
    @FXML private Label ip7;
    @FXML private Label nombre7;
    @FXML private Label version7;
    @FXML private Label ip8;
    @FXML private Label nombre8;
    @FXML private Label version8;

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

        // Actualizar cards con los hostnames
        updateCards();
        // Listeners para actualizar cards al cambiar los textfields
        puesto1.textProperty().addListener((obs, oldVal, newVal) -> card1.setText(newVal));
        puesto2.textProperty().addListener((obs, oldVal, newVal) -> card2.setText(newVal));
        puesto3.textProperty().addListener((obs, oldVal, newVal) -> card3.setText(newVal));
        puesto4.textProperty().addListener((obs, oldVal, newVal) -> card4.setText(newVal));
        puesto5.textProperty().addListener((obs, oldVal, newVal) -> card5.setText(newVal));
        puesto6.textProperty().addListener((obs, oldVal, newVal) -> card6.setText(newVal));
        puesto7.textProperty().addListener((obs, oldVal, newVal) -> card7.setText(newVal));
        puesto8.textProperty().addListener((obs, oldVal, newVal) -> card8.setText(newVal));
    }

    private void updateCards() {
        card1.setText(puesto1.getText());
        card2.setText(puesto2.getText());
        card3.setText(puesto3.getText());
        card4.setText(puesto4.getText());
        card5.setText(puesto5.getText());
        card6.setText(puesto6.getText());
        card7.setText(puesto7.getText());
        card8.setText(puesto8.getText());
        // Por defecto, los campos IP, Nombre y Version quedan vacíos
        ip1.setText("IP: "); nombre1.setText("Nombre: "); version1.setText("Version: ");
        ip2.setText("IP: "); nombre2.setText("Nombre: "); version2.setText("Version: ");
        ip3.setText("IP: "); nombre3.setText("Nombre: "); version3.setText("Version: ");
        ip4.setText("IP: "); nombre4.setText("Nombre: "); version4.setText("Version: ");
        ip5.setText("IP: "); nombre5.setText("Nombre: "); version5.setText("Version: ");
        ip6.setText("IP: "); nombre6.setText("Nombre: "); version6.setText("Version: ");
        ip7.setText("IP: "); nombre7.setText("Nombre: "); version7.setText("Version: ");
        ip8.setText("IP: "); nombre8.setText("Nombre: "); version8.setText("Version: ");
        // Bordes grises por defecto
        card1.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card2.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card3.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card4.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card5.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card6.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card7.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
        card8.getParent().setStyle("-fx-border-color: #cccccc; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
    }

    private void appendMultiline(String text) {
        StringBuilder sb = new StringBuilder();
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if (text.startsWith("-")) {
            int start = 0;
            while (start < text.length()) {
                int end = Math.min(start + MAX_LINE_LENGTH, text.length());
                sb.append("[").append(hora).append("] ").append(text.substring(start, end)).append("\n");
                start = end;
            }
        } else {
            sb.append("[").append(hora).append("] ").append(text).append("\n");
        }
        // Insertar al principio del TextArea
        String current = textArea.getText();
        textArea.setText(sb.toString() + current);

        // Procesar mensaje multicast para actualizar cards
        // Formato esperado: hostname=INF01;ipv4=192.168.5.107;usuario=admin;mensaje=Hola;version=2.0
        String[] parts = text.trim().split(";");
        String hostname = null, ipv4 = null, usuario = null, version = null, mensaje = null;
        for (String part : parts) {
            String[] kv = part.split("=", 2);
            if (kv.length == 2) {
                switch (kv[0].trim().toLowerCase()) {
                    case "hostname": hostname = kv[1].trim(); break;
                    case "ipv4": ipv4 = kv[1].trim(); break;
                    case "usuario": usuario = kv[1].trim(); break;
                    case "version": version = kv[1].trim(); break;
                    case "mensaje": mensaje = kv[1].trim(); break;
                }
            }
        }
        // Solo cambiar color si mensaje es Hola o Adios, o si el borde está en gris y llega cualquier mensaje
        String borderColor = null;
        if ("Hola".equalsIgnoreCase(mensaje)) borderColor = "#4CAF50"; // verde
        else if ("Adios".equalsIgnoreCase(mensaje)) borderColor = "#F44336"; // rojo
        // Si el mensaje no es Hola ni Adios, pero el borde está en gris, ponerlo en verde
        if (hostname != null) {
            if (hostname.equals(puesto1.getText())) {
                ip1.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre1.setText("Nombre: " + (usuario != null ? usuario : ""));
                version1.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card1.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card1.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto2.getText())) {
                ip2.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre2.setText("Nombre: " + (usuario != null ? usuario : ""));
                version2.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card2.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card2.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto3.getText())) {
                ip3.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre3.setText("Nombre: " + (usuario != null ? usuario : ""));
                version3.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card3.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card3.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto4.getText())) {
                ip4.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre4.setText("Nombre: " + (usuario != null ? usuario : ""));
                version4.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card4.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card4.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto5.getText())) {
                ip5.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre5.setText("Nombre: " + (usuario != null ? usuario : ""));
                version5.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card5.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card5.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto6.getText())) {
                ip6.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre6.setText("Nombre: " + (usuario != null ? usuario : ""));
                version6.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card6.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card6.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto7.getText())) {
                ip7.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre7.setText("Nombre: " + (usuario != null ? usuario : ""));
                version7.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card7.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card7.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            } else if (hostname.equals(puesto8.getText())) {
                ip8.setText("IP: " + (ipv4 != null ? ipv4 : ""));
                nombre8.setText("Nombre: " + (usuario != null ? usuario : ""));
                version8.setText("Version: " + (version != null ? version : ""));
                String currentStyle = ((javafx.scene.layout.VBox) card8.getParent()).getStyle();
                if (borderColor == null && (currentStyle == null || currentStyle.contains("#cccccc"))) borderColor = "#4CAF50";
                if (borderColor != null) card8.getParent().setStyle("-fx-border-color: " + borderColor + "; -fx-border-width: 2; -fx-background-color: #f9f9f9; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8;");
            }
        }
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
