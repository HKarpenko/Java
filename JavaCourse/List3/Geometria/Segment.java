package Geometria;

public class Segment {
    private Point P1;
    private Point P2;
    public Segment(Point punk1, Point punk2){
        P1=punk1;
        P2=punk2;
        if (punk1.cordx()==punk2.cordx() && punk1.cordy()==punk2.cordy())
            throw new ArithmeticException("nie mozna utworzyc odcinka o dlugosci 0");
    }
    Point get_start(){
        return P1;
    }
    Point get_end(){
        return P2;
    }
    double length() {
        return Math.sqrt(Math.pow(P1.cordx()-P2.cordx(),2)+Math.pow(P1.cordy()-P2.cordy(),2));
    }

    public void move(Wektor v){
        P1.move(v);
        P2.move(v);
    }

    public void rotate(Point p, double ang){
        P1.rotate(p, ang);
        P2.rotate(p, ang);
    }
    public void symmetry(Prosta p){
        P1.symmetry(p);
        P2.symmetry(p);
    }
}
