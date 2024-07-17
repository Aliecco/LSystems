public class Vec2D {
    double x, y;
    Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    Vec2D(Vec2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void turn(double angle) {
        double _x = Math.cos(angle) * this.x - Math.sin(angle) * this.y;
        this.y = Math.sin(angle) * this.x + Math.cos(angle) * this.y;
        this.x = _x;
    }
    public void move(Vec2D direction) {
        this.x += direction.x;
        this.y += direction.y;
    }

    public static double distance(Vec2D a, Vec2D b) {
        return  Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y,2));
    }

    public String toString() {
        return this.x + "," + this.y + ";";
    }
}
