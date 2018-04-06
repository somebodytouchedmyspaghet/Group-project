package Controller;

import View.ControlledScreen;
import static View.Main.MenuID;
import View.ScreensController;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class HighScoresController implements Initializable, ControlledScreen {
    ScreensController myController;
    Connection con;
    DBOperations op;
    DBConnection cn;

    @FXML  private AnchorPane Anchor;
    @FXML  private VBox vBox;
    
    public static int refresh = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cn=new DBConnection();
        op=new DBOperations();
        Anchor.addEventHandler(MouseEvent.ANY, (MouseEvent event) ->{
            if(refresh==0){
               
                con=cn.AccederBD();
                Object[][] scores = op.getHighScores(con);

                for(int i = 0; i<5; i++){
                    try{
                    HBox hBox = new HBox();                    
                    hBox.setSpacing(150);
                    if((String) scores[i][0]!=null){
                        hBox.getChildren().add(new Label((String) scores[i][0]));
                        hBox.getChildren().add( new Label((String) scores[i][1]));
                        hBox.getChildren().add(new Label((String) scores[i][2]));
                        vBox.getChildren().add(hBox);
                    }
                    }
                    catch(Exception e){
                    }
                }       
                refresh++;
            }    
          
        });

    }    

    @FXML
    private void backToMenu(ActionEvent event) {
        myController.setScreen(MenuID);
        vBox.getChildren().removeAll(vBox.getChildren());
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
                myController = screenParent  ;
    }
    
}
