/**
 * 
 */
/**
 * 
 */
module EjerciciosAF {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens model;
}