@echo off
setlocal
set JAVAFX_LIB=c:\localstorage\javafx-sdk-24.0.1\lib
set JAR=target\LibroController1-1.0.jar

java --module-path "%JAVAFX_LIB%" --add-modules javafx.controls,javafx.fxml -jar "%JAR%"