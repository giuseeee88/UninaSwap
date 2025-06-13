package com.uninaswap;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller {
	
	public static void creaTabelle() throws SQLException {
		Connection conn = Connessione.getConnection();
	    String query = "CREATE TABLE IF NOT EXISTS UTENTI("
	    		+ "id SERIAL PRIMARY KEY,"
	    		+ "username VARCHAR(20) NOT NULL UNIQUE,"
	    		+ "email VARCHAR(255) NOT NULL UNIQUE,"
	    		+ "password VARCHAR(255) NOT NULL"
	    		+ ");";
	    
		PreparedStatement statement = conn.prepareStatement(query);
		statement.executeUpdate();  	
		statement.close();
	}
	
	@FXML
	public void Prodotti(MouseEvent e) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("prodotti.fxml"));
	        Parent root = loader.load();
	        ControllerProdotti controllerProdotti = loader.getController();
	        
	        String pathImmagine = getClass().getResource("/uninaswap/Immagini/Logo.png").toExternalForm();
	        
	        controllerProdotti.impostaImmagineProfilo(pathImmagine);
	       

	        Scene scene = new Scene(root);
	        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        currentStage.setScene(scene);
	        currentStage.centerOnScreen();
	        currentStage.setTitle("UninaSwap - I miei prodotti");
	    }
		catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	@FXML
	public void Vendite(MouseEvent e) {
		System.out.println("Hai selezionato Vendite");
	}
	
	@FXML
	public void Scambi(MouseEvent e) {
		System.out.println("Hai selezionato Scambi");
	}
	
	@FXML
	public void Acquisti(MouseEvent e) {
		System.out.println("Hai selezionato Acquisti");
		try {
            Parent root = FXMLLoader.load(getClass().getResource("acquisti.fxml"));
            Scene scene = new Scene(root);
	        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        currentStage.setScene(scene);
	        currentStage.centerOnScreen();
	        currentStage.setTitle("UninaSwap - Acquisti");
        }
        
        catch(Exception ex) {
            ex.printStackTrace();
        }
	}
	
	@FXML
	public void mostraLogin(MouseEvent e) {
		try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
	        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        currentStage.setScene(scene);
	        currentStage.centerOnScreen();
	        currentStage.setTitle("UninaSwap - Login");
        }
        
        catch(Exception ex) {
            ex.printStackTrace();
        }
	}
	
	@FXML
    public void mostraRegistrazione(MouseEvent e) {
		try {
            Parent root = FXMLLoader.load(getClass().getResource("registrazione.fxml"));
            Scene scene = new Scene(root);
	        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        currentStage.setScene(scene);
	        currentStage.centerOnScreen();
	        currentStage.setTitle("UninaSwap - Regstrazione");
        }
        
        catch(Exception ex) {
            ex.printStackTrace();
        }
	}
}
