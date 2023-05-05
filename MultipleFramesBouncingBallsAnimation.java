import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {

    private static final int ZERO = 0;
    private static final int TWO = 2;
    /**
     * The Width of the Window.
     */
    private static final int WIDTH_WINDOW = 800;
    /**
     * The Height of the Window.
     */
    private static final int HEIGHT_WINDOW = 600;
    /**
     * The maximum RGB value for the balls colors.
     */
    private static final int RGB_MAX = 256;
    /**
     * The maximum angle for the balls velocities.
     */
    private static final int ANGLE_MAX = 180;
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
     * The Top left point of rectangle 1.
     */
    private static final Point START_RECTANGLE_1 = new Point(50, 50);
    /**
     * The Top left point of rectangle 2.
     */
    private static final Point START_RECTANGLE_2 = new Point(450, 450);
    /**
     * The Width rectangle 1.
     */
    private static final int WIDTH_RECTANGLE_1 = 450;
    /**
     * The Height rectangle 1.
     */
    private static final int HEIGHT_RECTANGLE_1 = 450;
    /**
     * The Width rectangle 2.
     */
    private static final int WIDTH_RECTANGLE_2 = 150;
    /**
     * The Height rectangle 2.
     */
    private static final int HEIGHT_RECTANGLE_2 = 150;

    /**
     * creates an array of the ball's sizes, that derives from the args
     * parameters. And afterwards starts the animation.
     *
     * @param args the sizes of the balls
     */
    public static void main(String[] args) {
        // creates sizes array
        int[] sizes = new int[args.length];
        // parses args into sizes
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = Integer.parseInt(args[i]);
        }
        drawAnimation(sizes);
    }


    /**
     * Draw the animation, firstly creates gui and creates an array to store
     * the balls, from there chooses a random starting point for each of
     * the balls, having the first half start somewhere in the first rectangle
     * and the other half start somewhere in the second rectangle.
     * from there it assigns the balls a velocity, depending on their size
     * and on a random angle.
     * Lastly it draws the balls accordingly, having the second rectangle
     * above the first.
     *
     * @param sizes the sizes
     */
    static void drawAnimation(int[] sizes) {
        // creates window of 800 by 600 pixels
        GUI gui = new GUI("title", WIDTH_WINDOW, HEIGHT_WINDOW);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        Ball[] balls = new Ball[sizes.length];

        for (int i = ZERO; i < balls.length; i++) {
            Point start;
            Point borderStart;
            Color color = new Color(rand.nextInt(RGB_MAX), rand.nextInt(RGB_MAX),
                    rand.nextInt(RGB_MAX));
            int borderWidth;
            int borderHeight;
            int speed;

            /*
            randomizes a starting point for each ball inside their
            corresponding rectangle
            */
            if (i < balls.length / TWO) {
                borderWidth = WIDTH_RECTANGLE_1;
                borderHeight = HEIGHT_RECTANGLE_1;
                start = new Point(rand.nextInt(WIDTH_RECTANGLE_1 - TWO * sizes[i])
                        + START_RECTANGLE_1.getX() + sizes[i],
                        rand.nextInt(HEIGHT_RECTANGLE_1 - TWO * sizes[i])
                                + START_RECTANGLE_1.getY() + sizes[i]);
                borderStart = START_RECTANGLE_1;
            } else {
                borderWidth = WIDTH_RECTANGLE_2;
                borderHeight = HEIGHT_RECTANGLE_2;
                start = new Point(rand.nextInt(WIDTH_RECTANGLE_2
                        - TWO * sizes[i])
                        + START_RECTANGLE_2.getX() + sizes[i],
                        rand.nextInt(WIDTH_RECTANGLE_2 - TWO * sizes[i])
                                + START_RECTANGLE_2.getY() + sizes[i]);
                borderStart = START_RECTANGLE_2;
            }

            // instantiates each ball into the array
            balls[i] = new Ball(start, sizes[i], color);

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
            // draw grey rectangle
            b.setColor(Color.GRAY);
            b.fillRectangle((int) START_RECTANGLE_1.getX(),
                    (int) START_RECTANGLE_1.getY(),
                    WIDTH_RECTANGLE_1, HEIGHT_RECTANGLE_1);
            // draw and move the grey rectangle's balls
            for (int i = ZERO; i < balls.length / 2; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(b);
            }
            // draw yellow rectangle
            b.setColor(Color.YELLOW);
            b.fillRectangle((int) START_RECTANGLE_2.getX(),
                    (int) START_RECTANGLE_2.getY(),
                    WIDTH_RECTANGLE_2, HEIGHT_RECTANGLE_2);
            // draw and move the yellow rectangle's balls
            for (int i = balls.length / TWO; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(b);
            }
            sleeper.sleepFor(FRAME_DELAY);
            // show frame
            gui.show(b);
        }
    }
}
