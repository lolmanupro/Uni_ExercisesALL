import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {

    private enum sidesE {
        LEFT,RIGHT,TOP,BOT
    }
    /**
     * The Upper left.
     */
    private Point upperLeft;
    /**
     * The Width.
     */
    private double width;
    /**
     * The Height.
     */
    private double height;

    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int TOP = 2;
    private final int BOT = 3;
    // 0 left, 1 right, 2 top, 3 bot
    Line[] sides = new Line[4];


    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left Point
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        sides[LEFT] = new Line (upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        sides[RIGHT] = new Line (upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        sides[TOP] = new Line (upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        sides[BOT] = new Line (upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
    }


    /**
     * Calculates intersection points between this rectangle and a given line.
     *
     * @param line the line
     * @return the list of intersection points
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public ArrayList<Point> intersectionPoints(Line line) {

        ArrayList<Point> list = new java.util.ArrayList<>();
        for (int i = LEFT; i <= BOT; i++) {
            list.add(line.intersectionWith(sides[i]));
        }
        return list;
    }

    public Boolean[] intersectionSides(Line line) {

        Boolean[] intersectionSides = new Boolean[4];
        for (int i = LEFT; i <= BOT; i++) {
            intersectionSides[i] = line.intersectionWith(sides[i]) != null;
        }
        return intersectionSides;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public Line[] getSides () {
        return this.sides;
    }

    public ArrayList<Line> onWhichSidesIsPoint(Point point) {
        // TODO: implement enum in rectangle to use for each here
        // intersection can happen with 2 sides at most
        ArrayList<Line> sides = new ArrayList<>();
        for (int i = LEFT; i <= BOT; i++) {
            if (this.sides[i].isPointInLine(point)) {
                sides.add(this.sides[i]);
            }
        }
        return sides;
    }
}