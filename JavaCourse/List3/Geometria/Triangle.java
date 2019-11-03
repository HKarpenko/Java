package Geometria;

public class Triangle {
    private Point Pn1;
    private Point Pn2;
    private Point Pn3;
    private static boolean oneside(Point P1, Point P2, Point P3, Point P4) {
        return ((P3.cordx()-P1.cordx())*(P2.cordy()-P1.cordy())-(P3.cordy()-P1.cordy())*(P2.cordx()-P1.cordx()))*
                ((P4.cordx()-P1.cordx())*(P2.cordy()-P1.cordy())-(P4.cordy()-P1.cordy())*(P2.cordx()-P1.cordx())) >= 0;
    }

    Triangle(Point punk1, Point punk2, Point punk3)
    {
        Pn1=punk1;
        Pn2=punk2;
        Pn3=punk3;
        Segment a = new Segment(punk1,punk2);
        Segment b = new Segment(punk2,punk3);
        Segment c = new Segment(punk3,punk1);
        if (a.length()+b.length()<=c.length() ||
                a.length()+c.length()<=b.length() ||
                c.length()+b.length()<=a.length())
            throw new ArithmeticException("nie mozna utworzyc trojkata o podanych wspolrzednych");
    }
    Point get_cord1(){
        return Pn1;
    }
    Point get_cord2(){
        return Pn2;
    }
    Point get_cord3(){
        return Pn3;
    }
    public void move(Wektor v){
        Pn1.move(v);
        Pn2.move(v);
        Pn3.move(v);
    }

    public void rotate(Point p, double ang){
        Pn1.rotate(p, ang);
        Pn2.rotate(p, ang);
        Pn3.rotate(p, ang);
    }
    public void symmetry(Prosta p){
        Pn1.symmetry(p);
        Pn2.symmetry(p);
        Pn3.symmetry(p);
    }
}
