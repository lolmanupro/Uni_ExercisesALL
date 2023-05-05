/**
 * The type Point.
 */
public class Point {

    private static final int TWO = 2;
    private static final double THRESHOLD = 0.000001d;
    ;

    /**
     * The X coordinate of this point.
     */
    private final double x;
    /**
     * The Y coordinate of this point.
     */
    private final double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between this point and the other point.
     *
     * @param other the other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, TWO)
                + Math.pow(this.y - other.y, TWO));
    }

    /**
     * Gets x.
     *
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Is point in range x boolean.
     *
     * @param range the range
     * @return the boolean
     */
    public boolean isPointInRangeX(Line range) {
        return (range.start().getX() <= x && x <= range.end().getX())
                || (range.end().getX() <= x && x <= range.start().getX());
    }

    /**
     * Is point in range y boolean.
     *
     * @param range the range
     * @return the boolean
     */
    public boolean isPointInRangeY(Line range) {
        return (range.start().y <= y && y <= range.end().y)
                || (range.end().y <= y && y <= range.start().y);
    }

    /**
     * Compares doubles using epsilon as a threshold.
     *
     * @param other the other point
     * @return the boolean
     */
    public boolean comparePoints(Point other) {
        return Math.abs(this.getX() - other.getX()) < THRESHOLD
                && Math.abs(this.getY() - other.getY()) < THRESHOLD;
    }
}