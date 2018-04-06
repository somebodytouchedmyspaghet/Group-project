
package Controller;

import View.ScreensController;
import View.ControlledScreen;
import static View.Main.FOOD_COLOR;
import static View.Main.HEIGHT;
import static View.Main.MenuID;
import static View.Main.SNAKE_COLOR;
import static View.Main.WIDTH;
import View.Painter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SnakeController implements Initializable, ControlledScreen {
    ScreensController myController;
    @FXML private Canvas canvas;
    private GameLoop loop;
    private Grid grid;
    private GraphicsContext graphicsContext;

    
    //Reset the game 
    public void reset() {
        
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, graphicsContext, SNAKE_COLOR, FOOD_COLOR);
        Painter.paint(grid, graphicsContext);
  
    } 
    //Initialization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            graphicsContext = canvas.getGraphicsContext2D();
            canvas.setFocusTraversable(true);
            canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (loop.isKeyPressed()) {
                return;
            }
            switch (e.getCode()) {
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                      break;
                  case LEFT:
                      snake.setLeft();
                      break;
                  case RIGHT:
                      snake.setRight();
                      break;
                  case ENTER:
                        if (loop.isPaused()) {
                        reset();
                        (new Thread(loop)).start();
                    }
                  case ESCAPE:
                        if (loop.isPaused()) {
                        reset();
                        (new Thread(loop)).start();
                        myController.setScreen(MenuID);                        
                    }
              }
          });
          
          reset();
          (new Thread(loop)).start();
}    
        @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent  ;
    }
}
