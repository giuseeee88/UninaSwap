package com.uninaswap;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerLogin extends Controller {	
	@FXML
    private PasswordField passwordField;
	
    @FXML
    private TextField visibleTextField;
    
    private boolean isPasswordLoginVisible = false;
    
    @FXML
    private ImageView immagineMostraPassword;
    
    @FXML
    private ImageView immagineNascondiPassword;
    
    @FXML
    private ImageView immagineProfilo;
	
    @FXML
	public void mostraPasswordLogin(MouseEvent e) {
		String text = "";
		if(!isPasswordLoginVisible) {
			text = passwordField.getText();
			passwordField.setVisible(false);
			visibleTextField.setText(text);
			visibleTextField.setVisible(true);
			isPasswordLoginVisible = true;
			immagineMostraPassword.setVisible(false);
			immagineNascondiPassword.setVisible(true);
		}
		
		else {
			text = visibleTextField.getText();
			visibleTextField.setVisible(false);
			passwordField.setText(text);
			passwordField.setVisible(true);
			isPasswordLoginVisible = false;
			immagineNascondiPassword.setVisible(false);
			immagineMostraPassword.setVisible(true);
		}
	}
}
