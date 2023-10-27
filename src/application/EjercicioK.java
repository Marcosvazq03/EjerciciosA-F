package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EjercicioK extends Application{

	@Override
	public void start(Stage primaryStage) {
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioKfxml.fxml"));
            Scene scene = new Scene(root,400,300);
            //scene.getStylesheets().add(getClass() .getResource("/css/estilosPersona.css").toExternalForm());
            primaryStage.setTitle("RELOJ");
            primaryStage.getIcons().add(new Image(getClass().getResource("/img/EjercicioKimg/reloj.png").toString()));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }
	
}
