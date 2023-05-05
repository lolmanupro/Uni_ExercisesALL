/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    /**
     * The Dx.
     */
    private double dx;
    /**
     * The Dy.
     */
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * returns dx.
     *
     * @return dx
     */
    public double dx() {
        return dx;
    }

    /**
     * returns dy.
     *
     * @return dy
     */
    public double dy() {
        return dy;
    }

    /**
     * Apply velocity to point.
     *
     * @param p the point
     * @return the point with acquired velocity
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * calculates the velocity using trigonometry, the angle and the speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }

}