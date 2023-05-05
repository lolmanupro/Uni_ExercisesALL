import biuoop.DrawSurface;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;

public class Block implements Collidable, Sprite{

    private Rectangle rect;
    private Point upperLeft;
    private final double width;
    private final double height;
    private final Color color;

    public Block (Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    public Rectangle getRectangle () {
        return this.rect;
    }

    public void setUpperLeft (Point upperLeft) {
        this.upperLeft = upperLeft;
        this.rect = new Rectangle(upperLeft, this.width, this.height);
    }

    public Color getColor () {
        return this.color;
    }
    /**
     * @return
     */
    @Override
    public Rectangle getCollisionRectangle(double r) {
        return new Rectangle(new Point(rect.getUpperLeft().getX() - r,
                rect.getUpperLeft().getY() - r),
                rect.getWidth() + 2*r, rect.getHeight() + 2*r);
    }

    /**
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, double r) {
        Velocity newVelocity = currentVelocity;
        Rectangle colRect = getCollisionRectangle(r);
        Boolean[] collisionSides = colRect.intersectionSides(new Line(collisionPoint, collisionPoint));

        if (collisionSides[0] || collisionSides[1]) {
            newVelocity = new Velocity(-newVelocity.dx(), newVelocity.dy());
        }
        if (collisionSides[2] || collisionSides[3]) {
            newVelocity = new Velocity(newVelocity.dx(), -newVelocity.dy());
        }

        return newVelocity;
    }


    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) width, (int) height);
    }

    /**
     *
     */
    @Override
    public void timePassed() {

    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
