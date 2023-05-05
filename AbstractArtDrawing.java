import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The type Abstract art drawing.
 */
public class AbstractArtDrawing {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    /**
     * The width of the window.
     */
    private static final int WIDTH_WINDOW = 400;
    /**
     * The height of the window.
     */
    private static final int HEIGHT_WINDOW = 300;
    /**
     * The amount of lines in the window.
     */
    private static final int LINE_COUNT = 10;
    /**
     * The radius of the lines intersection points.
     */
    private static final int INTERSECTION_POINT_SIZE = 3;
    /**
     * The radius of the lines middle points.
     */
    private static final int MIDDLE_POINT_SIZE = 3;

    /**
     * draws random lines calling drawRandomLines in a non-static fashion.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }

    /**
     * Generate random line within the screen.
     *
     * @return the line
     */
    static Line generateRandomLine() {
        Random rand = new Random();
        return new Line(rand.nextInt(WIDTH_WINDOW) + ONE,
                rand.nextInt(HEIGHT_WINDOW) + ONE,
                rand.nextInt(WIDTH_WINDOW) + ONE,
                rand.nextInt(HEIGHT_WINDOW) + ONE);
    }

    /**
     * Draw line on a given surface using the biuoop package.
     *
     * @param l the line
     * @param d the surface
     */
    static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * Draw random lines, firstly making an array of all lines,
     * and then coloring blue the middle of each line and coloring
     * red the intersections between lines.
     */
    public void drawRandomLines() {
        /*
        Create a window with the title "Random Circles Example"
        which is 400 pixels wide and 300 pixels high.
        */
        GUI gui = new GUI("Random Circles Example", WIDTH_WINDOW,
                HEIGHT_WINDOW);
        DrawSurface d = gui.getDrawSurface();
        // creates array of lines
        Line[] lines = new Line[LINE_COUNT];
        // generates each random line into the array and draws them
        for (int i = ZERO; i < lines.length; i++) {
            lines[i] = generateRandomLine();
            drawLine(lines[i], d);
        }

        for (Line value : lines) {
            // paints middle points of lines
            d.setColor(Color.blue);
            d.fillCircle((int) value.middle().getX(),
                    (int) value.middle().getY(), MIDDLE_POINT_SIZE);

            for (Line line : lines) {
                if (line != value) {
                    // calculates intersection point of lines
                    Point intersection = value.intersectionWith(line);

                    if (intersection != null) {
                        // paints intersection points of lines
                        d.setColor(Color.red);
                        d.fillCircle((int) intersection.getX(),
                                (int) intersection.getY(),
                                INTERSECTION_POINT_SIZE);
                    }
                }
            }
        }
        gui.show(d);
    }


}
