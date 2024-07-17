import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] vec = {0,5};
        double[] loc = {5,5};
        double angle = Math.toRadians(90);
        var rules = new HashMap<Character, String>();

        rules.put('F', "F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF");
        rules.put('f', "ffffff");

//        rules.put('F', "F-F++F-F");
//        rules.put('X', "F[-X][+X]");

//        rules.put('X', "F[-X]F[-X]+X");
//        rules.put('F', "F[+F]F[-F]F");

//        rules.put('B', "-A+B+A-");
//        rules.put('A', "+B-A-B+");
//        rules.put('Y', "-FX-Y");
//        rules.put('X', "X+YF+");
        String command = Turtle.doRule("F+F+F+F", rules, 2);
        System.out.println(command);
        System.out.println();
        var turtle = new Turtle(command, loc, vec, angle);
        var lines = Turtle.doCommand(turtle);

        System.out.println(lines.size());
        Renderer renderer = new Renderer(10, lines);
        renderer.makeimg(lines);
    }
}