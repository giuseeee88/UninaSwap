package com.uninaswap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
	
	@Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UninaSwap - Login");
            stage.getIcons().add(
                new Image(getClass().getResource("/uninaswap/Immagini/Logo.png").toExternalForm())
            );
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
