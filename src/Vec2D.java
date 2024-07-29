public class Vec2D {
    double x, y;

    Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double diagonal_distance(Vec2D a, Vec2D b) {
        return  Math.max(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
    }

    public static double lerp(double x, double y, double t) {
        return (1 - t) * x + t * y;
    }

    public String toString() {
        return "[" + this.x + ":" + this.y + "]";
    }
}