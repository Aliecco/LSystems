import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Turtle {
    String command;
    double[] location;
    double[] vector;
    double angle;

    public Turtle(String command, double[] location, double[] vector, double angle) {
        this.command = command;
        this.location = location;
        this.vector = vector;
        this.angle = angle;
    }

    public static String doRule(String command, HashMap<Character, String> rule, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res = new StringBuilder();
            for(char c : command.toCharArray()) {
                res.append(rule.getOrDefault(c, String.valueOf(c)));
            }
            command = res.toString();
        }
        return res.toString();
    }

    public static ArrayList<Line> doCommand(Turtle turtle) {
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < turtle.command.length(); i++) {
            char ch = turtle.command.charAt(i);
            switch (ch) {
//              move forward
                case 'F':
                case 'A':
                case 'B':
//                case 'Y':
                case 'X':
                    var line = new Line();
                    line.setStart(new Vec2D(turtle.location[0], turtle.location[1]));
                    turtle.location[0] += turtle.vector[0];
                    turtle.location[1] += turtle.vector[1];
                    line.setEnd(new Vec2D(turtle.location[0], turtle.location[1]));
                    lines.add(line);
                    break;
//              step over
                case 'f':
                    turtle.location[0] += turtle.vector[0];
                    turtle.location[1] += turtle.vector[1];
                    break;
//              turn left
                case '+':
                    turtle.changeDir(turtle.angle);
                    break;
//              turn right
                case '-':
                    turtle.changeDir(-turtle.angle);
                    break;
//              bracketed OL-systems
                case '[':
                    String command;
                    int counter = 1;
                    for(int j = i + 1; j < turtle.command.length(); j++) {
                        if(turtle.command.charAt(j) == '[') {
                            counter++;
                        } else if (turtle.command.charAt(j) == ']' && counter > 1) {
                            counter--;
                        } else if(turtle.command.charAt(j) == ']' && counter == 1){
                            command = turtle.command.substring(i, j);
                            lines.addAll(doCommand(new Turtle(command,
                                    new double[]{turtle.location[0], turtle.location[1]},
                                    new double[]{turtle.vector[0], turtle.vector[1]},
                                    turtle.angle)));
                            i=j;
                            break;
                        }
                    }
            }
        }

        return lines;
    }

     void changeDir(double angle){
        double x1 = vector[0] * Math.cos(angle) - vector[1] * Math.sin(angle);
        double y1 = vector[0] * Math.sin(angle) + vector[1] * Math.cos(angle);
        vector[0] = x1;
        vector[1] = y1;
    }
}
