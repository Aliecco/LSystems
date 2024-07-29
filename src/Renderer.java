import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer {
    Vec2D size;
    Line canvas;
    int scale;
    Renderer(int scale, List<Line> lines) {
        this.scale = scale;
        this.setSize(lines);
    }

    record Point (int x, int y, Color color){}

    public void makeimg(List<Line> lines, String fileName) throws IOException {
        int sizeX = (int) Math.round(this.size.x*this.scale);
        int sizeY = (int) Math.round(this.size.y*this.scale);
        BufferedImage img = new BufferedImage(sizeX+this.scale/2, sizeY+this.scale/2, BufferedImage.TYPE_INT_RGB);
        List<Point> points = new ArrayList<>();
        for (Line line: lines) {
            points.addAll(line(line, new Color(255,255,255)));
        }
        File image = new File(fileName + ".png");
        ImageIO.write(draw(img, points), "png", image);
    }

    public void setSize(List<Line> lines) {
        double[] xArr = new double[lines.size()*2];
        double[] yArr = new double[lines.size()*2];
        int i = 0;
        for (Line line: lines) {
            xArr[i] = line.start.x;
            yArr[i] = line.start.y;
            xArr[++i] = line.end.x;
            yArr[i] = line.end.y;
            ++i;
        }
        double xMax = Arrays.stream(xArr).max().getAsDouble();
        double xMin = Arrays.stream(xArr).min().getAsDouble();
        double yMax = Arrays.stream(yArr).max().getAsDouble();
        double yMin = Arrays.stream(yArr).min().getAsDouble();
        double xSize = xMax - xMin + 1;
        double ySize = yMax - yMin + 1;
        this.size = new Vec2D(xSize,ySize);
        this.canvas = new Line(new Vec2D(xMax,yMax),new Vec2D(xMin,yMin));
    }

    private List<Point> line(Line line, Color color) {//this gets a list of discrete points
        List<Point> result = new ArrayList<>();
        double n = Vec2D.diagonal_distance(line.start, line.end);
        if (n == 0) result.add(new Point((int)  Math.round(line.start.x), (int)  Math.round(line.start.y), color));
        //for (double step = 0.0; step <= n; step =+ ((double) 1 / this.scale))
        for (int step = 0; step <= n*this.scale; ++step)
        {
            double t = ((double) step)/this.scale / n;
            var lerp = line.lerp(t);
            result.add(new Point((int)  Math.round(lerp.x * this.scale),(int)  Math.round(lerp.y * this.scale), color));
        }
        return result;
    }

    public BufferedImage draw(BufferedImage image, List<Point> points) {
        for (Point point: points) {
            image.setRGB(
                    point.x + (int)  Math.round(Math.abs(this.canvas.end.x * this.scale)),
                    point.y + (int)  Math.round(Math.abs(this.canvas.end.y * this.scale)),
                    point.color.getRGB()
            );
        }
        return image;
    }
}