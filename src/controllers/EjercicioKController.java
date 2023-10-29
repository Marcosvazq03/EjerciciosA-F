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
    	
    	if (num==0) {
    		img = "ZERO";
		} 		
    	if (num==1) { 
			img = "ONE";
    	}
    	if (num==2) {
			img = "TWO";
    	}
    	if (num==3) { 
			img = "THREE";
    	}
    	if (num==4) {
			img = "FOUR";
    	}
    	if (num==5) {
			img = "FIVE";
    	}
    	if (num==6) {
			img = "SIX";
    	}
    	if (num==7) {
			img = "SEVEN";
    	}
    	if (num==8) {
			img = "EIGHT";
    	}
    	if (num==9) {
			img = "NINE";
    	}
		
    	return img;
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	        	//Sacar segundos
	            int segundos = LocalDateTime.now().getSecond();
	            String strSegundos = segundos<=9 ? "0"+segundos:""+segundos;
	           
	            int s2= Integer.parseInt(strSegundos.charAt(0) + "");
	            int s1= Integer.parseInt(strSegundos.charAt(1) + "");
	            imgS1.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(s1)+".png").toString()));
	            imgS2.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(s2)+".png").toString()));
				
	            //Sacar minutos
	            int minutos = LocalDateTime.now().getMinute();
	            String strMinutos = minutos<=9 ? "0"+minutos:""+minutos;
	           
	            int m2= Integer.parseInt(strMinutos.charAt(0) + "");
	            int m1= Integer.parseInt(strMinutos.charAt(1) + "");
	            imgM1.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(m1)+".png").toString()));
	            imgM2.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(m2)+".png").toString()));
	            
	            //Sacar horas
	            int horas = LocalDateTime.now().getHour();
	            String strHoras = horas<=9 ? "0"+horas:""+horas;
	           
	            int h2= Integer.parseInt(strHoras.charAt(0) + "");
	            int h1= Integer.parseInt(strHoras.charAt(1) + "");
	            imgH1.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(h1)+".png").toString()));
	            imgH2.setImage(new Image(getClass().getResource("/img/EjercicioKimg/"+selectImg(h2)+".png").toString()));
	        }
	    };
	    timer.scheduleAtFixedRate(task, 0, 1000);
	}

}
