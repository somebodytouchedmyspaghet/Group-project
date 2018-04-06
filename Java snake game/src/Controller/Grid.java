package Controller;

import java.util.Random;
import javafx.scene.paint.Color;
//Code for the grid itself meaning the play area
public class Grid {
    
    public static final int SIZE = 10;
    public static final Color COLOR = new Color(0, 0, 0, 1);

    private final int cols;     
    private final int rows;    

    private Snake snake;
    private Food food;

    public Grid(final double width, final double height) {
        rows = (int) width / SIZE;
        cols = (int) height / SIZE;

        snake = new Snake(this, new Point(rows / 2, cols / 2));

        food = new Food(getRandomPoint());
    }
//if the snake passes a certain point he will die if he runs into himself he dies
    public Point wrap(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x >= rows) {snake.safe=false; }
        if (y >= cols) {snake.safe=false; }
        if (x <= 0){snake.safe=false; }
        if (y <= 0) {snake.safe=false; }
        return new Point(x, y);
    }
//generates the random point on the map
    private Point getRandomPoint() {
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows-5), random.nextInt(cols-5));
        } while (point.equals(snake.getHead()));
        return point;
    }

    public void update() {
        if (food.getPoint().equals(snake.getHead())) {
            snake.extend();
            food.setPoint(getRandomPoint());
        } else {
            snake.move();
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double getWidth() {
        return rows * SIZE;
    }

    public double getHeight() {
        return cols * SIZE;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}