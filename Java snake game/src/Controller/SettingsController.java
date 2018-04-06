* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ControlledScreen;
import View.Main;
import static View.Main.MenuID;
import View.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import static View.Main.SNAKE_COLOR;
import static View.Main.FOOD_COLOR;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * 
 */
//Controls the settings menu
public class SettingsController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML Label diffLabel;
    
    // Go back to the main menu
    @FXML private void goBackToMenu(){
        myController.setScreen(MenuID);
    }
    //Set snake color to green
    @FXML private void setSnakeColorGREEN(){
        SNAKE_COLOR = Color.web("#00ff33"); 
        System.out.println("working green");
    }
    //Set snake color ro red
    @FXML private void setSnakeColorRED(){
        SNAKE_COLOR = Color.RED; 
        System.out.println("working red");
    }
    //Set snake color to blue
    @FXML private void setSnakeColorBLUE(){
        SNAKE_COLOR = Color.web("#0001ff");
        System.out.println("working blue");
    }
    //Set food color to green
    @FXML private void setFoodColorGREEN(){
        FOOD_COLOR = Color.web("#00ff33"); 
        System.out.println("working green");
    }
    //Set food color to red
    @FXML private void setFoodColorRED(){
        FOOD_COLOR = Color.RED; 
        System.out.println("working red");
    }
    //Set food color to blue
    @FXML private void setFoodColorBLUE(){
        FOOD_COLOR = Color.web("#0001ff");
        System.out.println("working blue");
    }
    
    //Set easy difficulty 
    @FXML private void setEasy(){
        Main.DIFF = 20;
        diffLabel.setText("EASY");
    }
    //Set medium difficulty
    @FXML private void setMedium(){
        Main.DIFF = 30;
        diffLabel.setText("medium");
    }
    //Set hard difficulty
    @FXML private void setHard(){
        Main.DIFF = 45;        
        diffLabel.setText("hard");
    }
    //Set expert difficulty 
    @FXML private void setExpert(){
        Main.DIFF = 50;
        diffLabel.setText("expert");
    }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent  ;
    }
    
}