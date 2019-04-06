/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class Interpreter extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Scene scene = new Scene(root);
//        
//        ScreenController screenController = new ScreenController(scene);
//        screenController.add("firstPage", FXMLLoader.load(getClass().getResource( "FXMLDocument.fxml" )));
//        screenController.add("secondPage", FXMLLoader.load(getClass().getResource( "SecondFXML.fxml" )));
//        
//        stage.setScene(scene);       
//        while(true){
//            screenController.activate("firstPage");
//            screenController.activate("secondPage");
//            stage.show();
//        }
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
