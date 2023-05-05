import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Bouncing ball animation.
 */
public class BouncingBallAnimation {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 2;
    private static final int WIDTH_WINDOW = 200;
    private static final int HEIGHT_WINDOW = 200;
    private static final int BALL_SIZE = 30;
    private static final Point START_WINDOW = new Point(0, 0);
    private static final int FRAME_DELAY = 50;

    /**
     * The entry point of application.
     *
     * @param args the x, y of the start, the dx and dy.
     */
    public static void main(String[] args) {
        drawAnimation(new Point(Integer.parseInt(args[ZERO]),
                        Integer.parseInt(args[ONE])),
                Integer.parseInt(args[TWO]), Integer.parseInt(args[THREE]));
    }

    /**
     * Draws the animation for the ball.
     *
     * @param start the starting point of the ball.
     * @param dx    the x-axis velocity of the ball.
     * @param dy    the y-axis velocity of the ball.
     */
    static void drawAnimation(Point start, double dx, double dy) {
        // creates window
        GUI gui = new GUI("title", WIDTH_WINDOW, HEIGHT_WINDOW);
        Sleeper sleeper = new Sleeper();
        // creates new black ball of size 30 in given starting point
        Ball ball = new Ball(new Point((int) start.getX(), (int) start.getY()),
                BALL_SIZE, java.awt.Color.BLACK);
        // sets the ball's velocity
        ball.setVelocity(dx, dy);
        while (true) {
            // moves ball
//            ball.moveOneStep();
            // creates new frame
            DrawSurface d = gui.getDrawSurface();
            // draws ball
            ball.drawOn(d);
            // shows frame
            gui.show(d);
            // wait for FRAME_DELAY milliseconds
            sleeper.sleepFor(FRAME_DELAY);
        }
    }
}
