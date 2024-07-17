public class Line {
    public Vec2D start;
    public Vec2D end;

    Line() {

    }
    Line(Vec2D start, Vec2D end){
        this.start = start;
        this.end = end;
    }

    public void setStart(Vec2D start){
        this.start = start;
    }
    public void setEnd(Vec2D end){
        this.end = end;
    }

    public static double distance(Line line, Vec2D point) {
        return
                Math.abs(
                        (line.end.y - line.start.y)*point.x - (line.end.x - line.start.x)*point.y + line.end.x*line.start.y - line.end.y*line.start.x
                )
                        /
                        Math.sqrt(
                                Math.pow((line.end.y - line.start.y),2.0) + Math.pow((line.end.x - line.start.x),2.0)
                        );
    }
}
