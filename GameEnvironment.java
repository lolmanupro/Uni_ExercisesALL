import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    /**
     * The Collidables.
     */
    ArrayList<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Add collidable.
     *
     * @param c the collidable
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    public CollisionInfo getClosestCollision(Line trajectory, double r) {
        ArrayList<Point> collisions = new java.util.ArrayList<>();
        Point min = null;
        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle(r);
            collisions.add(trajectory.closestIntersectionToStartOfLine(rect));
        }
        //collisions.add(trajectory.closestIntersectionToStartOfLine(collidables.get(1).getCollisionRectangle(r)));

        Collidable collider = null;

        for (Point collision : collisions) {
            if (collision != null
                    && (min == null || (trajectory.start().distance(collision)
                    <= trajectory.start().distance(min)))) {
                min = collision;
            }
        }

        for (int i = 0; i < collidables.size(); i++) {
            if (collidables.get(i) == null) {
                continue;
            }
            Collidable col = collidables.get(i);
            if (col != null && min != null && !col.getCollisionRectangle(r).onWhichSidesIsPoint(min).isEmpty()) {
                collider = col;
            }
        }

        return new CollisionInfo(min, collider);
    }
}