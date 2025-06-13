package com.uninaswap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerRegistrazione extends Controller {

    //Campi registrazione
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField emailField;
    
	@FXML
    private PasswordField passwordField;
	
    @FXML
    private PasswordField confermaPasswordField;
    
    @FXML
    private TextField visibleFieldRegistrazione;
    
	//Mostra/Nascondi password registrazione
    
    @FXML
    private TextField visibleConfermaPassword;
    
    private boolean isPasswordRegistrazioneVisible = false;
    
    @FXML
    private ImageView immagineMostraPassword;
    
    @FXML
    private ImageView immagineNascondiPassword;
    
    //Alert dati non validi
    
    @FXML
    private Label elencoProblematiche;
    
    @FXML
    private Label messaggioDinamico;
    
    @FXML
    private Label chiudiAlertRegistrazione;
    
    @FXML
	private Pane alertRegistrazione;
    
    @FXML
    private Pane pannelloRegistrazione;
    
    //Alert utente già esistente
    
    @FXML
    private Pane alertUtenteEsistente;
    
    @FXML
    private Label chiudiAlertUtenteEsistente;
    
    @FXML
    private Label schermataLogin;
    
    @FXML
    public void mostraPasswordRegistrazione(MouseEvent e) {
		String text = "";
		String textConferma = "";
		
		if(!isPasswordRegistrazioneVisible) {
			text = passwordField.getText();
			passwordField.setVisible(false);
			visibleFieldRegistrazione.setText(text);
			visibleFieldRegistrazione.setVisible(true);
			
			textConferma = confermaPasswordField.getText();
			confermaPasswordField.setVisible(false);
			
			visibleConfermaPassword.setText(textConferma);
			visibleConfermaPassword.setVisible(true);
			
			isPasswordRegistrazioneVisible = true;
			immagineMostraPassword.setVisible(false);
			immagineNascondiPassword.setVisible(true);
		}
		
		else {
			text = visibleFieldRegistrazione.getText();
			visibleFieldRegistrazione.setVisible(false);
			passwordField.setText(text);
			passwordField.setVisible(true);
			
			textConferma = visibleConfermaPassword.getText();
			visibleConfermaPassword.setVisible(false);
			confermaPasswordField.setText(textConferma);
			confermaPasswordField.setVisible(true);
			
			isPasswordRegistrazioneVisible = false;
			immagineNascondiPassword.setVisible(false);
			immagineMostraPassword.setVisible(true);
		}
	}
    
	@FXML
	public void registrazione(MouseEvent e) throws SQLException {
		String username = this.usernameField.getText().trim();
		String email = this.emailField.getText().trim();		
		String password = this.passwordField.getText().trim();
		String confermaPassword = this.confermaPasswordField.getText().trim();
		
		if(controllaCampi(username, email, password, confermaPassword)) {
			Connection conn = Connessione.getConnection();
			String query = "SELECT * FROM UTENTI WHERE username = ? OR email = ?;";
        	PreparedStatement checkEsistenzaUtente = conn.prepareStatement(query);
        	checkEsistenzaUtente.setString(1, username);
        	checkEsistenzaUtente.setString(2, email);
        	
        	if(!checkEsistenzaUtente.executeQuery().next()) {
	            query = "INSERT INTO UTENTI("
	            		+ "username, email, password) "
	            		+ "VALUES (?, ?, ?);";
	            
	        	PreparedStatement statement = conn.prepareStatement(query);
	        	statement.setString(1, username);
	        	statement.setString(2, email);
	        	statement.setString(3, password);
	        	statement.executeUpdate();
	        	
	        	System.out.println("Registrazione avvenuta con successo!");
	        	
	        	statement.close();
	        	
	    		mostraLogin(e);
	        }
        	
        	else {
        		System.out.println("Username o email già esistenti");
        		alertUtenteEsistente.setVisible(true);
        		pannelloRegistrazione.setVisible(false);
        	}
		}
	}
	
	private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	
	private boolean controllaCampi(String username, String email, String password, String confermaPassword){
		String elencoProblematicheText = "";
		String messaggioDinamicoText = "";
		boolean credenzialiValide = true;
		
		if(username.isEmpty() || username.length() < 2 || username.length() > 20) {
			elencoProblematicheText += "- Username non valido\n";
			messaggioDinamicoText += "Inserire un username lungo minimo 2 caratteri e massimo 20 caratteri\n";
			credenzialiValide = false;
		}
		
		if(email.isEmpty() || !isValidEmail(email) || email.length() > 255) {
			elencoProblematicheText += "- Email non valida\n";
			messaggioDinamicoText += "Inserire un'email con un formato valido e lunga massimo 255 caratteri\n";
			credenzialiValide = false;
		}
		
		if(password.isEmpty() || password.length() < 8 || password.length() > 20) {
			elencoProblematicheText += "- Password non valida\n";
			messaggioDinamicoText += "Inserire una password lunga minimo 2 caratteri e massimo 20 caratteri\n";
			credenzialiValide = false;
		}
		
		if(confermaPassword.isEmpty() || !password.equals(confermaPassword)) {
			elencoProblematicheText += "- Password non corrispondenti\n";
			messaggioDinamicoText += "La conferma della password deve corrispondere con la password inserita";
			credenzialiValide = false;
		}
		
		if(!credenzialiValide) {
			elencoProblematiche.setText(elencoProblematicheText);
			messaggioDinamico.setText(messaggioDinamicoText);
			alertRegistrazione.setVisible(true);
			pannelloRegistrazione.setVisible(false);
		}
		
		return credenzialiValide;
	}
	
	public void chiudiAlertRegistrazione(MouseEvent e) {
		alertRegistrazione.setVisible(false);
		pannelloRegistrazione.setVisible(true);
	}
	
	public void chiudiAlertUtenteEsistente(MouseEvent e) {
		alertUtenteEsistente.setVisible(false);
		pannelloRegistrazione.setVisible(true);
	}
	
	public void schermataLogin(MouseEvent e) {
		alertUtenteEsistente.setVisible(false);
		pannelloRegistrazione.setVisible(true);
		mostraLogin(e);
	}
}