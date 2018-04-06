package View;

import Controller.DBConnection;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
//the main class for the game 
public class Main extends Application {
    public static String MenuID = "Menu";
    public static String MenuFX = "Menu.fxml";
    public static String SnakeID = "Snake";
    public static String SnakeFX = "Snake.fxml";
    public static String HighScoresID = "HighScores";
    public static String HighScoresFX = "HighScores.fxml";
    public static String SettingsID = "Settings";
    public static String SettingsFX = "Settings.fxml";

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static ArrayList<Score> Scores = new ArrayList<Score>();
    
    public static Color SNAKE_COLOR = Color.web("#00ff33");
    public static Color FOOD_COLOR = Color.web("#FF0000");
    
    public static int DIFF = 20;
    
    DBConnection cn;


    @Override
    public void start(Stage stage) throws Exception {
        cn=new DBConnection();
        cn.CrearBD();
        cn.cerracon();
        ScreensController mainContainer = new ScreensController();         

        mainContainer.loadScreen(MenuID, MenuFX);   
        mainContainer.loadScreen(SnakeID, SnakeFX);  
        mainContainer.loadScreen(HighScoresID, HighScoresFX);  
        mainContainer.loadScreen(SettingsID, SettingsFX);  
        mainContainer.setScreen(MenuID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Snake Game By Lucas, Denzel and Andy.");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));

        stage.show();    

    }


    
    public static void main(String[] args) {
        Application.launch(args);
    }

}