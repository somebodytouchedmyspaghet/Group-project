package Controller;


import static View.Main.SNAKE_COLOR;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.List;

// Snake class
public class Snake {
    public static final Color COLOR = SNAKE_COLOR; // snake color
    public static final Color DEAD = Color.RED;
    private Grid grid; 
    private int length;  //length of snake
    public boolean safe; // if snake is safe or not
    private List<Point> points;  // Players points
    private Point head; // head of the snake 
    private int xVelocity; // x velocity of snake
    private int yVelocity; // y velocity of snake 

    // Constructor
    public Snake(Grid grid, Point initialPoint) {
        length = 1;
        points = new LinkedList<>();
        points.add(initialPoint);
        head = initialPoint;
        safe = true;
        this.grid = grid;
        xVelocity = 0;
        yVelocity = 0;
    }

    //Make snake grow
    private void growTo(Point point) {
        length++;
        checkAndAdd(point);
    }

    //Remove point when snake eats it
    private void shiftTo(Point point) {
        checkAndAdd(point);
        points.remove(0);
    }

    //Giving the user points
    private void checkAndAdd(Point point) {
        point = grid.wrap(point);
        safe &= !points.contains(point);
        points.add(point);
        head = point;
    }

    //Where points are stored
    public List<Point> getPoints() {
        return points;
    }

    //When the snake starts the game at length 1
    public boolean isSafe() {
        return safe || length == 1;        
    }

    //getter for head
    public Point getHead() {
        return head;
    }

    //checks if the snake is staying still
    private boolean isStill() {
        return xVelocity == 0 & yVelocity == 0;
    }

    //Moves the snake around
    public void move() {
        if (!isStill()) {
            shiftTo(head.translate(xVelocity, yVelocity));
        }
    }

    //extend the length of the snake 
    public void extend() {
        if (!isStill()) {
            growTo(head.translate(xVelocity, yVelocity));
        }
    }

    //move up
    public void setUp() {
        if (yVelocity == 1 && length > 1) return;
        xVelocity = 0;
        yVelocity = -1;
    }

    //move down
    public void setDown() {
        if (yVelocity == -1 && length > 1) return;
        xVelocity = 0;
        yVelocity = 1;
    }
    //move left
    public void setLeft() {
        if (xVelocity == 1 && length > 1) return;
        xVelocity = -1;
        yVelocity = 0;
    }
    //move right
    public void setRight() {
        if (xVelocity == -1 && length > 1) return;
        xVelocity = 1;
        yVelocity = 0;
    }
}