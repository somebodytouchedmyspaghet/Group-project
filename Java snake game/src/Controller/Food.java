package Controller;

import static View.Main.FOOD_COLOR;
import javafx.scene.paint.Color;

//class for the food declaring the color and point
public class Food {
    public static final Color COLOR = FOOD_COLOR;

    private Point point;

    Food(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
} 

