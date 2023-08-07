package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginScreenController {

    @FXML
    private Button btnIniciar;

    @FXML
    private Button btnParar;

    @FXML
    private TextArea consoleTextArea; // Componente TextArea para mostrar a saída do console

    private Process process; // Variável para armazenar o processo da API
    private OutputStream processOutputStream; // Variável para armazenar a saída da API
    private Thread consoleUpdater; // Thread para atualizar o TextArea com a saída da API

    @FXML
    public void handleButtonPress(ActionEvent event) {

        if (process == null) { // Verifica se a API já foi iniciada
            // Inicia a API ricktraderapi.jar
            try {
                String jarPath = "/RickTraderApi/ricktraderapi.jar";
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", jarPath);
                process = pb.start(); // Armazena o processo em uma variável para uso posterior
                processOutputStream = process.getOutputStream(); // Redireciona a saída da API para a variável

                // Habilita o botão "Parar" quando a API é iniciada
                btnParar.setDisable(false);

                // Desabilita o botão "Iniciar" após a API ser iniciada
                btnIniciar.setDisable(true);

                // Redireciona a saída do processo para o TextArea
                InputStream processInputStream = process.getInputStream();
                consoleUpdater = new Thread(() -> {
                    byte[] buffer = new byte[1024];
                    try {
                        int bytesRead;
                        while ((bytesRead = processInputStream.read(buffer)) != -1) {
                            String outputText = new String(buffer, 0, bytesRead);
                            Platform.runLater(() -> appendToConsole(outputText));
                        }
                    } catch (IOException e) {
                        // API foi parada, a exceção será lançada ao fechar o stream
                    }
                });
                consoleUpdater.setDaemon(true);
                consoleUpdater.start();
            } catch (IOException e) {
                e.printStackTrace();

                // Exibe um alerta de erro caso ocorra uma exceção
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Erro ao iniciar a API RickTrader");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleButtonStop(ActionEvent event) {
        if (process != null) {
            process.destroy(); // Encerra o processo da API
            try {
                process.getOutputStream().close(); // Fecha o stream de saída para parar a thread consoleUpdater
            } catch (IOException e) {
                e.printStackTrace();
            }
            process = null; // Define a variável process como null para indicar que a API foi parada
            processOutputStream = null; // Define a variável de saída do processo como null

            // Habilita o botão "Iniciar" após a API ser parada
            btnIniciar.setDisable(false);

            // Desabilita o botão "Parar" quando a API é parada
            btnParar.setDisable(true);

            // Limpa o conteúdo do TextArea
            Platform.runLater(() -> consoleTextArea.clear());
        }
    }

    // Método para adicionar a saída do console ao TextArea
    private void appendToConsole(String text) {
        consoleTextArea.appendText(text);
    }
}
