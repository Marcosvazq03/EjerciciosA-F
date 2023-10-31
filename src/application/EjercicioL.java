package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EjercicioL extends Application{

	@Override
	public void start(Stage primaryStage) {
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioLfxmlLogin.fxml"));
            Scene scene = new Scene(root,300,200);
            //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
            primaryStage.setTitle("AVIONES-LOGIN");
            primaryStage.getIcons().add(new Image(getClass().getResource("/img/EjercicioLimg/avion.png").toString()));
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
