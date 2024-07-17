import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer {
    Vec2D size;
    Line canvas;
    int scale;
    Renderer(int scale, List<Line> lines){
        this.scale = scale;
        this.setSize(lines);
    }


    //public void makeimg(List<Line> lines) throws IOException {
    //    int ocr, ocg, ocb;
    //    int sizeY = (int) size.y*scale;
    //    int sizeX = (int) size.x*scale;
    //    BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
    //    Color rgbval = null;
    //    for(int i=0; i<sizeX; i++){
    //        for(int j=0; j<sizeY; j++){
    //            rgbval = new Color(255,255,255);
    //            img.setRGB(i, j, rgbval.getRGB());
    //            for (Line line: lines) {
    //                if (Renderer.intersects(((double) i/scale)/,((double) j/scale)/, line)){
    //                    rgbval = new Color(0,0,0);
    //                    img.setRGB(i, j, rgbval.getRGB());
    //                }
    //            }
    //        }
    //    }
    //    File image = new File("image.png");
    //    ImageIO.write(img, "png", image);
    //}

    public void makeimg(List<Line> lines) throws IOException {
        int ocr, ocg, ocb;
        int sizeX = (int) (this.size.x*scale);
        int sizeY = (int) (this.size.y*scale);
        double stepX = this.size.x/sizeX;
        double stepY = this.size.y/sizeY;
        BufferedImage img = new BufferedImage(sizeX+10, sizeY+10, BufferedImage.TYPE_INT_RGB);
        Color rgbval = null;
        for(double i=this.canvas.end.x; i<=this.canvas.start.x; i += stepX){
            for(double j=this.canvas.end.y; j<=this.canvas.start.y; j += stepY){
                for (Line line: lines) {
                    if (Renderer.intersects(i, j, line)){
                        rgbval = new Color(255,255,255);
                        img.setRGB(
                                (int) Math.floor(Math.abs((-i + this.canvas.start.x))/stepX)+5,
                                (int) Math.floor(Math.abs((-j + this.canvas.start.y))/stepY)+5,
                                rgbval.getRGB());
                    }
                }
            
                
            }
        }
        File image = new File("image.png");
        ImageIO.write(img, "png", image);
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
        double xSize = xMax - xMin;
        double ySize = yMax - yMin;
        this.size = new Vec2D(xSize,ySize);
        this.canvas = new Line(new Vec2D(xMax,yMax),new Vec2D(xMin,yMin));
    }



    public static boolean intersects(double x, double y, Line line) {
        Vec2D a = new Vec2D(line.start.x,line.start.y);
        Vec2D b = new Vec2D(line.end.x,line.end.y);
        Vec2D c = new Vec2D(x,y);
        double ac = Vec2D.distance(a,c);
        double bc = Vec2D.distance(b,c);
        double ab = Vec2D.distance(a,b);
        double linedist = Line.distance(line, c);
        boolean res = Math.abs((Vec2D.distance(a,c) + Vec2D.distance(b,c)) - Vec2D.distance(a,b)) <= 0.1 && Math.abs(Line.distance(line, c)) <= 0.1;
        return res;
    }
}
