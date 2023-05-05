import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private final int screenHeight = 600;
    private final int screenWidth = 800;
    private final int bordersOffset = 20;
    private GUI gui;

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    public GameEnvironment getEnvironment () {
        return this.environment;
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("title", 800, 600);
        Ball ball = new Ball(new Point(284.095107669, 43.7115576734), 20, Color.blue);
        ball.setVelocity(new Velocity(-8.959709467909631, -4.441126687075084));
        ball.addToGame(this);

        Block b1 = new Block(new Point(0, bordersOffset), bordersOffset, screenHeight, Color.GRAY);
        Block b2 = new Block(new Point(0, 0), screenWidth, bordersOffset, Color.GRAY);
        Block b3 = new Block(new Point(screenWidth - bordersOffset, bordersOffset), bordersOffset, screenHeight, Color.GRAY);
        Block b4 = new Block(new Point(bordersOffset, screenHeight - bordersOffset), screenWidth, bordersOffset, Color.GRAY);

        int counter = 0;
        Block[] blocks = new Block[57];
        double BLOCK_START_HEIGHT = (double)screenHeight/4;
        double BLOCK_HEIGHT = 20;
        double BLOCK_WIDTH = 50;

        for (int i = 12; i > 6; i--) {
            for (int j = 0; j < i; j++) {
                final double x = screenWidth - bordersOffset - BLOCK_WIDTH * (j + 1) - j;
                switch (i) {
                    case 12 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT),BLOCK_WIDTH, BLOCK_HEIGHT, Color.gray);
                    case 11 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT + BLOCK_HEIGHT*1),BLOCK_WIDTH, BLOCK_HEIGHT, Color.pink);
                    case 10 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT + BLOCK_HEIGHT*2 ),BLOCK_WIDTH, BLOCK_HEIGHT, Color.orange);
                    case 9 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT + BLOCK_HEIGHT*3 ),BLOCK_WIDTH, BLOCK_HEIGHT, Color.yellow);
                    case 8 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT + BLOCK_HEIGHT*4 ),BLOCK_WIDTH, BLOCK_HEIGHT, Color.blue);
                    case 7 -> blocks[counter] = new Block (new Point(x, BLOCK_START_HEIGHT + BLOCK_HEIGHT*5) ,BLOCK_WIDTH, BLOCK_HEIGHT, Color.red);
                }
                counter ++;
            }
        }

        for (Block block : blocks) {
            if (block != null)
                block.addToGame(this);
        }
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);

        Paddle p = new Paddle(this.gui, new Block (new Point ((double)800/2, 610 - 50), 100, 20, Color.red));
        p.addToGame(this);

    }

    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 40;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}