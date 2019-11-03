package Geometria;

public class Prosta {
    public final double a;
    public final double b;
    public final double c;

    public Prosta(double _a, double _b, double _c) {
        if(_a==0 && _b==0)
            throw new ArithmeticException();
        a = _a;
        b = _b;
        c = _c;
    }

    public static Prosta przesun(Prosta p, Wektor w) {
        return new Prosta(p.a, p.b, p.c - p.b * w.dy + p.a * w.dx);
    }

    public static boolean parallel(Prosta p1, Prosta p2) {
        if((p1.a==0 && p2.a==0) || (p1.b==0 && p2.b==0) )
        return true;
        return ((p1.a / p2.a) == (p1.b / p2.b));
    }

    public static boolean perpendicular(Prosta p1, Prosta p2) {
        return ((p1.a * p2.a) == -(p1.b * p2.b));
    }
    public static Point intersection(Prosta p1, Prosta p2){
        if(parallel(p1,p2))
            throw new ArithmeticException("Proste są równoległe");
        double z = (p1.a*p2.b - p2.a*p1.b);
        double x = -(p1.c*p2.b - p1.b*p2.c) / z;
        double y = -(p1.a*p2.c - p2.a*p1.c) / z;
        return new Point(x,y);
    }
}
