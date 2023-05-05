//import biuoop.DrawSurface;
//import biuoop.GUI;
//import biuoop.Sleeper;
//
//import java.awt.Color;
//import java.util.Random;
//
//public class Test {
//    public static void main(String[] args) {
//        GUI gui = new GUI("title", 200, 200);
//        Sleeper sleeper = new Sleeper();
//        Random rand = new Random();
//
//        Ball b = new Ball(new Point(100, 100), 20, Color.blue);
//        b.setVelocity(Velocity.fromAngleAndSpeed(120, 10));
//
//        Block screen1 = new Block(new Point(0, 0), 10, 200);
//        Block screen2 = new Block(new Point(0, 0), 200, 10);
//        Block screen3 = new Block(new Point(190, 0), 10, 200);
//        Block screen4 = new Block(new Point(0, 190), 200, 10);
//
//
//        GameEnvironment environment = new GameEnvironment();
//        environment.addCollidable(screen1);
//        environment.addCollidable(screen2);
//        environment.addCollidable(screen3);
//        environment.addCollidable(screen4);
//        b.setEnvironment(environment);
//
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
//            b.drawOn(d);
//            screen1.drawOn(d, Color.GRAY);
//            screen2.drawOn(d, Color.GRAY);
//            screen3.drawOn(d, Color.GRAY);
//            screen4.drawOn(d, Color.GRAY);
//
//            b.moveOneStep();
//
//            sleeper.sleepFor(100);
//            gui.show(d);
//        }
//    }
//}