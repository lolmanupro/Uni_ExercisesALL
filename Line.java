import java.util.ArrayList;

/**
 * The type Line.
 */
public class Line {

    /**
     * The Start.
     */
    private Point start;
    /**
     * The End.
     */
    private Point end;
    /**
     * The X 1.
     */
    private double x1;
    /**
     * The X 2.
     */
    private double x2;
    /**
     * The Y 1.
     */
    private double y1;
    /**
     * The Y 2.
     */
    private double y2;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        x1 = start.getX();
        x2 = end.getX();
        y1 = start.getY();
        y2 = end.getY();
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x for the start point
     * @param y1 the y for the start point
     * @param x2 the x for the end point
     * @param y2 the y for the end point
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * calculates the length of this line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculate the middle point of the line using its equation,
     * if it is a vertical line use the difference between the y coordinates ]
     * for the middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double m1, b1;
        m1 = (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
        b1 = this.start.getY() - m1 * this.start.getX();

        if (m1 == Double.POSITIVE_INFINITY) {
            return new Point(this.x1, (this.y1 + this.y2) / 2);
        }

        return new Point((x1 + x2) / 2, m1 * (x1 + x2) / 2 + b1);
    }

    /**
     * Start point.
     *
     * @return the starting point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the ending point of the line
     */
    public Point end() {
        return end;
    }

    public boolean isPointInLine (Point point) {
        double[] thisEq = getEquation(this);
        if (Math.abs(thisEq[0]) == Double.POSITIVE_INFINITY || Math.abs(thisEq[1]) == Double.POSITIVE_INFINITY ) {
            return isIntersecting(new Line (point, point));
        }
        return point.comparePoints
                (new Point(point.getX(),thisEq[0]*point.getX() + thisEq[1]));
    }

    public double[] getEquation (Line line) {
        double m1, b1;

        // calculates the line equation using analytic geometry
        m1 = (line.start.getY() - line.end.getY())
                / (line.start.getX() - line.end.getX());
        b1 = line.start.getY() - m1 * line.start.getX();

        return new double[]{m1,b1};
    }
    /**
     * Checks in both axis whether a starting or ending point of one
     * line resides in the other, or whether a line is inside the other.
     *
     * @param other the other line
     * @return whether this line and the other intersect
     */
    public boolean isIntersecting(Line other) {
        return (other.start.isPointInRangeX(this)
                || other.end.isPointInRangeX(this)
                || this.start.isPointInRangeX(other)
                || this.end.isPointInRangeX(other))
                &&
                (other.start.isPointInRangeY(this)
                        || other.end.isPointInRangeY(this)
                        || this.start.isPointInRangeY(other)
                        || this.end.isPointInRangeY(other));
    }

    /**
     * calculates the intersection between two lines, firstly calculating
     * the equation of each line and then checking for intersections.
     *
     * @param other the other line
     * @return the point of intersection between this line and the other line, null if they are the same line.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other) || this.equals(other)) {
            return null;
        }

        double[] thisEq = getEquation(this);
        double[] otherEq = getEquation(other);
        // TODO: implement enum here to need these declarations
        double m1, m2, b1, b2;
        m1 = thisEq[0];
        b1 = thisEq[1];
        m2 = otherEq[0];
        b2 = otherEq[1];

        // calculates the lines intersection using analytic geometry
        Point intersection = new Point((b2 - b1) / (m1 - m2),
                m1 * (b2 - b1) / (m1 - m2) + b1);

        // if both lines are vertical
        if (Math.abs(m1) == Double.POSITIVE_INFINITY && Math.abs(m2) == Double.POSITIVE_INFINITY) {
            return null;
        // if first line is vertical, plot his x in the other's equation
        } else if (Math.abs(m1) == Double.POSITIVE_INFINITY) {
            intersection = new Point(this.start.getX(),
                    m2 * this.start.getX() + b2);
            // if second line is vertical, plot his x in the other's equation
        } else if (Math.abs(m2) == Double.POSITIVE_INFINITY) {
            intersection = new Point(other.start.getX(),
                    m1 * other.start.getX() + b1);
        }

        // checks if intersection point isn't on both lines
        if (intersection.distance(other.start) > other.length()
                || intersection.distance(other.end) > other.length()
                || intersection.distance(this.start) > this.length()
                || intersection.distance(this.end) > this.length()) {
            return null;
        }

        return intersection;
    }

    /**
     * Checks whether two lines are equal, comparing their starts and ends.
     *
     * @param other the other line
     * @return whether this line and the other line are equal.
     */
    public boolean equals(Line other) {
        return (start.equals(other.start) && end.equals(other.end))
                || (start.equals(other.end) && end.equals(other.start));
    }

    /**
     * Calculate the closest intersection point to start of line between
     * this line and a given rectangle.
     *
     * @param rect the rectangle
     * @return the closest intersection point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);

        Point min = intersectionPoints.get(0);

        for (Point p: intersectionPoints) {
            if (p != null) {
                min = p;
                break;
            }
        }

        for (Point point: intersectionPoints) {
            if (min != null && point != null && this.start().distance(point) <= this.start().distance(min))
                min = point;
        }
        return min;

    }
}