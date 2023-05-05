import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {

    private static final int ZERO = 0;
    private static final int TWO = 2;
    /**
     * The Minimum Size of a Big ball.
     */
    private static final int BIG_SIZE_MIN = 50;
    /**
     * The Minimum Size of a Medium ball.
     */
    private static final int MEDIUM_SIZE_MIN = 30;
    /**
     * The Speed of every big ball.
     */
    private static final int BIG_BALL_SPEED = 2;
    /**
     * The Small Ball's rand threshold for speed.
     */
    private static final int MEDIUM_BALL_SPEED_RAND = 3;
    /**
     * The Medium Ball's min speed.
     */
    private static final int MEDIUM_BALL_SPEED_OFFSET = 3;
    /**
     * The Small Ball's rand threshold for speed.
     */
    private static final int SMALL_BALL_SPEED_RAND = 3;
    /**
     * The Small Ball's min speed.
     */
    private static final int SMALL_BALL_SPEED_OFFSET = 5;
    /**
     * The Frame Delay for the animation.
     */
    private static final int FRAME_DELAY = 10;
    /**
     * The Top left point of screen.
     */
    static final Point START_WINDOW = new Point(0, 0);
    /**
     * The maximum angle for the balls velocities.
     */
    private static final int ANGLE_MAX = 180;
    /**
     * The height of the window.
     */
    static final int HEIGHT_WINDOW = 200;
    /**
     * The width of the window.
     */
    static final int WIDTH_WINDOW = 200;


    /**
     * The entry point of application.
     *
     * @param args the size of the balls
     */
    public static void main(String[] args) {
        // creates sizes array
        int[] sizes = new int[args.length];
        // parses args into sizes
        for (int i = ZERO; i < sizes.length; i++) {
            sizes[i] = Integer.parseInt(args[i]);
        }
        drawAnimation(sizes);
    }


    /**
     * Draw the animation, firstly creates gui and creates an array to store
     * the balls, from there chooses a random starting point for each of
     * the balls.
     * from there it assigns the balls a velocity, depending on their size
     * and on a random angle.
     *
     * @param sizes the sizes
     */
    static void drawAnimation(int[] sizes) {
        // creates window of 200 by 200 pixels
        GUI gui = new GUI("title", WIDTH_WINDOW, HEIGHT_WINDOW);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        // creates balls array
        Ball[] balls = new Ball[sizes.length];

        for (int i = 0; i < balls.length; i++) {
            int speed;
            // randomizes a starting point for each ball inside the window
            Point start = new Point(rand.nextInt(WIDTH_WINDOW - TWO * sizes[i])
                    + START_WINDOW.getX() + sizes[i],
                    rand.nextInt(HEIGHT_WINDOW - TWO * sizes[i])
                    + START_WINDOW.getY() + sizes[i]);

            // instantiates each ball into the array
            balls[i] = new Ball(start, sizes[i], Color.black);

            // gives bigger ball slower speeds
            if (sizes[i] >= BIG_SIZE_MIN) {
                speed = BIG_BALL_SPEED;
            } else if (sizes[i] >= MEDIUM_SIZE_MIN) {
                speed = rand.nextInt(MEDIUM_BALL_SPEED_RAND)
                        + MEDIUM_BALL_SPEED_OFFSET;
            } else {
                speed = rand.nextInt(SMALL_BALL_SPEED_RAND)
                        + SMALL_BALL_SPEED_OFFSET;
            }

            // randomizes an angle in radians for a velocity to the ball
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(
                    rand.nextInt(ANGLE_MAX) * Math.PI / ANGLE_MAX, speed));
        }


        while (true) {
            // create new frame
            DrawSurface b = gui.getDrawSurface();
            // move and draw balls
            for (int i = ZERO; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(b);
            }
            sleeper.sleepFor(FRAME_DELAY);
            // show frame
            gui.show(b);
        }
    }
}
