package com.uninaswap;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerProdotti extends Controller {
	@FXML
    private ImageView immagineProfilo;

    public void impostaImmagineProfilo(String pathImmagine) {
    	if(pathImmagine != null) immagineProfilo.setImage(new Image(pathImmagine));
    	else immagineProfilo.setImage(new Image("/uninaswap/Immagini/Logo.png"));
    }
}
