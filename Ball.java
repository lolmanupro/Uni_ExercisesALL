import biuoop.DrawSurface;

import java.awt.*;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    /**
     * The Center.
     */
    private Point center;
    /**
     * The R.
     */
    private final int r;
    /**
     * The Color.
     */
    private final java.awt.Color color;
    /**
     * The Dx.
     */
    private double dx;
    /**
     * The Dy.
     */
    private double dy;
    /**
     * The Border height.
     */
    private int borderHeight;
    /**
     * The Border width.
     */
    private int borderWidth;
    /**
     * The Border start.
     */
    private Point borderStart;

    /**
     * The GameEnvironment.
     */
    private GameEnvironment gm;

    /**
     * Instantiates a new Ball.
     *
     * @param center      the center
     * @param r           the r
     * @param color       the color

     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.borderStart = borderStart;

    }

    /**
     * Gets x coordinate of the ball's center.
     *
     * @return the coordinate
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y coordinate of the ball's center.
     *
     * @return the coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on a given surface using the biuoop package.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     *
     */
    @Override
    public void timePassed() {
        System.out.println("center coords: " + this.center.getX() + ", " + this.center.getY());
        System.out.println("vel: " + this.getVelocity().dx() + ", " + this.getVelocity().dy());
        moveOneStep();
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        setEnvironment(g.getEnvironment());
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.dx = v.dx();
        this.dy = v.dy();
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setEnvironment(GameEnvironment gm) {
        this.gm = gm;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.dx, this.dy);
    }

    /**
     * Moves the balls center one step, depending on its velocity,
     * and changes it if the balls hits a border.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, new Point
                (this.center.getX() + dx , this.center.getY() + dy));
        CollisionInfo col = gm.getClosestCollision(trajectory, this.r);

        if (col == null || col.collisionPoint() == null || col.collisionObject() == null) {
            this.center = trajectory.end();
        } else {
            this.setVelocity(col.collisionObject().hit(col.collisionPoint(), this.getVelocity(), this.r));
            this.center = new Point (this.center.getX() + dx, this.center.getY() + dy);
        }
    }
}
