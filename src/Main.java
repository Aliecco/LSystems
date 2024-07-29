import java.io.IOException;
import java.util.*;

public class Main {
    static HashMap<Character, String> rules = new HashMap<>();
    static String axiom;
    static double[] vec = {0,-5};
    static double[] loc = {0,0};
    static double angle = Math.toRadians(60);

    public static void main(String[] args) throws IOException {
        kochSnowflake();
        sierpinskisTriangle();
        arrowWeed();
        fuzzyWeed();
    }

    static void kochSnowflake() throws IOException {
        angle = Math.toRadians(60);
        axiom = "F++F++F";
        rules.put('F', "F-F++F-F");
        var command = Turtle.doRule(axiom, rules, 4);

        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines, "koch snowflake");
    }

    static void sierpinskisTriangle() throws IOException {
        angle = Math.toRadians(60);
        axiom = "A";
        rules.put('B', "-A+B+A-");
        rules.put('A', "+B-A-B+");
        var command = Turtle.doRule(axiom, rules, 7);

        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines, "Sierpinski's triangle");
    }

    static void arrowWeed() throws IOException {
        angle = Math.toRadians(30);
        axiom = "X";
        rules.put('F', "FF");
        rules.put('X', "F[+X][-X]FX");
        var command = Turtle.doRule(axiom, rules, 5);

        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines, "arrow weed");
    }

    static void fuzzyWeed() throws IOException {
        angle = Math.toRadians(22.5);
        axiom = "X";
        rules.put('F', "FF");
        rules.put('X', "F-[[X]+X]+F[+FX]-X");
        var command = Turtle.doRule(axiom, rules, 5);

        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines, "fuzzy weed");
    }

    static void squareIslands() throws IOException {

        angle = Math.toRadians(90);
        rules.put('F', "F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF");
        rules.put('f', "ffffff");

        var command = Turtle.doRule("F+F+F+F", rules, 4);

        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines, "square islands");
    }
}