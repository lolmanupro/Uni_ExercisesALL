import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final double SCREEN_WIDTH;
    private final double SCREEN_HEIGHT;
    Block paddle;

    public Paddle (GUI gui, Block paddle) {
        this.keyboard = gui.getKeyboardSensor();
        this.SCREEN_WIDTH = gui.getDrawSurface().getWidth();
        this.SCREEN_HEIGHT = gui.getDrawSurface().getHeight();
        this.paddle = paddle;
    }

    public void moveLeft() {
        if (paddle.getRectangle().getUpperLeft().getX() > 0) {
            Point p = new Point(paddle.getRectangle().getUpperLeft().getX() - 5, paddle.getRectangle().getUpperLeft().getY());
            paddle.setUpperLeft(p);
        }
    }
    public void moveRight() {
        if (paddle.getRectangle().getUpperLeft().getX() < SCREEN_WIDTH) {
            Point p = new Point(paddle.getRectangle().getUpperLeft().getX() + 5, paddle.getRectangle().getUpperLeft().getY());
            paddle.setUpperLeft(p);
        }
    }

    /**
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        paddle.drawOn(d);
    }

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @param r
     * @return
     */
    @Override
    public Rectangle getCollisionRectangle(double r) {
        return paddle.getCollisionRectangle(r);
    }

    /**
     * @return
     */
    @Override
    public Rectangle getRectangle() {
        return paddle.getRectangle();
    }

    /**
     * @param collisionPoint
     * @param currentVelocity
     * @param r
     * @return
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, double r) {

        Double[] regions = new Double[5];
        double colUpperLeftX = getCollisionRectangle(r).getUpperLeft().getX();
        for (int i = 0; i < regions.length; i++) {
            regions[i] = i*(this.getRectangle().getWidth()/5);
        }

        if (collisionPoint.getX() <= regions[0] + colUpperLeftX) {
            return Velocity.fromAngleAndSpeed(300, Math.sqrt(Math.pow(currentVelocity.dx(), 2) + Math.pow(currentVelocity.dy(), 2)));
        }
        if (collisionPoint.getX() <= regions[1] + colUpperLeftX) {
            return Velocity.fromAngleAndSpeed(330, Math.sqrt(Math.pow(currentVelocity.dx(), 2) + Math.pow(currentVelocity.dy(), 2)));
        }
        if (collisionPoint.getX() <= regions[2] + colUpperLeftX) {
            return this.paddle.hit(collisionPoint, currentVelocity, r);
        }
        if (collisionPoint.getX() <= regions[3] + colUpperLeftX) {
            return Velocity.fromAngleAndSpeed(30, Math.sqrt(Math.pow(currentVelocity.dx(), 2) + Math.pow(currentVelocity.dy(), 2)));
        }
        if (collisionPoint.getX() <= regions[4] + colUpperLeftX + r) {
            return Velocity.fromAngleAndSpeed(60, Math.sqrt(Math.pow(currentVelocity.dx(), 2) + Math.pow(currentVelocity.dy(), 2)));
        }

        return currentVelocity;
    }
}