package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EjercicioJ extends Application{

	@Override
	public void start(Stage primaryStage) {
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/fxml/EjercicioJfxml.fxml"));
            root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            Scene scene = new Scene(root,480,460);
            //scene.getStylesheets().add(getClass() .getResource("/css/estilosPersona.css").toExternalForm());
            primaryStage.setTitle("MINI COOPER");
            primaryStage.getIcons().add(new Image(getClass().getResource("/img/EjercicioJimg/Cooper.png").toString()));
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
