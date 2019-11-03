package Geometria;

public class Wektor {
    public final double dx;
    public final double dy;
    public Wektor(double _dx, double _dy){
        dx=_dx;
        dy=_dy;
    }
    public static Wektor add(Wektor w1, Wektor w2){
        return new Wektor(w1.dx+w2.dx,w1.dy+w2.dy);
    }
}
