package edu.miracosta.cs112.lotaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Get the FXML file as a resource
            URL fxmlUrl = getClass().getResource("/Users/munikklowas/IdeaProjects/l08-loteria-KlowasMunik/src/main/resources/edu/miracosta/cs112/lotaria/loteriaintellij/hello-view.fxml");

            // Load the FXML file
            Parent root = FXMLLoader.load(fxmlUrl);

            // Create and show the scene
            Scene scene = new Scene(root, 350, 500);
            stage.setTitle("EChALE STEM Loteria!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}