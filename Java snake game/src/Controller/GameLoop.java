package Controller;
import View.Main;
import View.Painter;
import java.sql.Connection;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

public class GameLoop implements Runnable {
    private final Grid grid;
    private final GraphicsContext context;
    public  int DIFFICULTY = 20;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;
    public static Label scoreLabel;
    
    Connection con;
    DBOperations op;
    DBConnection cn;
    
    public GameLoop(final Grid grid, final GraphicsContext context, Color SNAKE_COLOR, Color FOOD_COLOR) {
        cn=new DBConnection();
        op=new DBOperations();
        con=cn.AccederBD();
        
        this.grid = grid;
        this.context = context;
        DIFFICULTY = Main.DIFF;
        interval = 1000.0f / DIFFICULTY; 
        running = true;
        paused = false;
        keyIsPressed = false;

    }

 //Code letting you update the Highscores
public void updateHighscoresBoard(){
    Platform.runLater(() -> {
        TextInputDialog dialog = new TextInputDialog("PLAYER");
DialogPane dialogPane = dialog.getDialogPane();
dialogPane.getStylesheets().add(getClass().getResource("/View/Style.css").toExternalForm());
dialogPane.getStyleClass().add("Dialog");
        dialog.setTitle("");
        dialog.setGraphic(null);
        dialog.setHeaderText("GAME OVER");                
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
//Displays there name and what difficulty they got there hightscore on
        if (result.isPresent()){
            String playerName = result.get();
            try{
                String sDiff="";
                switch (DIFFICULTY) {
                    case 20:
                        sDiff = "EASY";
                        break;
                    case 30:
                        sDiff = "MEDIUM";
                        break;
                    case 45:
                        sDiff = "HARD";
                        break;
                    case 50:
                        sDiff = "EXPERT";
                        break;
                    default:
                        break;
                }
                op.insert("HighScores","name,score,diff","'" + playerName+ "','"+(grid.getSnake().getPoints().size()*100)+ "','"+sDiff+"'",con);
            }catch(Exception e){
                e.printStackTrace();
            }
            }
    });

}
    @Override
    public void run() {
        while (running && !paused) {
            // Time the update and paint calls
            float time = System.currentTimeMillis();

            keyIsPressed = false;
            grid.update();
            Painter.paint(grid, context);
            
            if (!grid.getSnake().isSafe()||grid.getSnake().getHead().getY()>=grid.getCols()-1 ||grid.getSnake().getHead().getY()<=-1||
                    grid.getSnake().getHead().getX()>=grid.getRows()-1 ||grid.getSnake().getHead().getX()<=-1) {
                pause();
                updateHighscoresBoard();
                Painter.paintResetMessage(context);
                break;
            } 

            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    public void setKeyPressed() {
        keyIsPressed = true;
    }

    public void resume() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

}