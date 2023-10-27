package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class EjercicioKController implements Initializable{

	@FXML
    private ImageView imgH1;

    @FXML
    private ImageView imgH2;

    @FXML
    private ImageView imgM1;

    @FXML
    private ImageView imgM2;

    @FXML
    private ImageView imgS1;

    @FXML
    private ImageView imgS2;
    
    private String selectImg(int num) {
    	String img = "";
    	switch (num) {
	    	case 0: {
				img = "ZERO";
			}
			case 1: {
				img = "ONE";
			}
			case 2: {
				img = "TWO";
			}
			case 3: {
				img = "THREE";
			}
			case 4: {
				img = "FOUR";
			}
			case 5: {
				img = "FIVE";
			}
			case 6: {
				img = "SIX";
			}
			case 7: {
				img = "SEVEN";
			}
			case 8: {
				img = "EIGHT";
			}
			case 9: {
				img = "NINE";
			}
		}
    	return img;
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	            int segundos = LocalDateTime.now().getSecond();
	            String strSegundos = segundos<=9 ? "0"+segundos:""+segundos;
	           
	            int s2= Integer.parseInt(strSegundos.charAt(0) + "");
	            int s1= Integer.parseInt(strSegundos.charAt(1) + "");
	            
	            imgS1.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(s1)+".png").toString()));
	            imgS2.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(s2)+".png").toString()));
				
	        }
	    };
	    timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
