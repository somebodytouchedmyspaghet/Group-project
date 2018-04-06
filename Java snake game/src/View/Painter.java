/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import Controller.Grid;
import static Controller.Grid.SIZE;
import Controller.Point;
import Controller.Snake;
import static View.Main.FOOD_COLOR;
import static View.Main.SNAKE_COLOR;
import javafx.scene.text.Font;

public class Painter {


    public static void paint(Grid grid, GraphicsContext gc) {
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

        // Now the Food
        gc.setFill(FOOD_COLOR);
        paintPoint(grid.getFood().getPoint(), gc);

        // Now the snake
        Snake snake = grid.getSnake();
        gc.setFill(SNAKE_COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }

        // The score
        
        gc.setFill(Color.web("#00ff33"));
        gc.setFont(new Font("Arcade Normal",12));
        gc.fillText("Score : " + snake.getPoints().size()*100, 10,20);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.web("#00ff33"));
        gc.fillText("Press ENTER to start a new game.", 200, 260);
        gc.fillText("Press ESC to go back to menu.", 220, 300);
    }
}