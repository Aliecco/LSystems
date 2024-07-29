public class Line {
    public Vec2D start;
    public Vec2D end;

    Line () {

    }

    Line(Vec2D start, Vec2D end){
        this.start = start;
        this.end = end;
    }

    public void setStart(Vec2D start) {
        this.start = start;
    }

    public void setEnd(Vec2D end) {
        this.end = end;
    }

    public Vec2D lerp(double t) {
        return new Vec2D(Vec2D.lerp(this.start.x,this.end.x,t),Vec2D.lerp(this.start.y,this.end.y,t));
    }

    public String toString() {
        return this.start + "-" + this.end;
    }
}