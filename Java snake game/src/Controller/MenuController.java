* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ControlledScreen;
import static View.Main.HighScoresID;
import static View.Main.SettingsID;
import static View.Main.SnakeID;
import View.ScreensController;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static Controller.HighScoresController.refresh;

public class MenuController implements Initializable, ControlledScreen {
    ScreensController myController;
        Connection con;
    DBOperations op;
    DBConnection cn;
    @FXML private void startNewGame(){
        myController.setScreen(SnakeID);
        System.out.println("Start New Game" );
    }
    
    @FXML private void goToHighscores(){
        refresh = 0;        
        myController.setScreen(HighScoresID);
        System.out.println("To Highscores" );
    }

    @FXML private void goToSettings(){
        myController.setScreen(SettingsID);        
        System.out.println("To Settings" );        
    }
    
    @FXML private void exit(){        
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cn=new DBConnection();
        op=new DBOperations();
        con=cn.AccederBD();
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent  ;
    }
    
}