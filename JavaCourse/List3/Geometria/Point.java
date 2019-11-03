package Geometria;

public class Point {
    private double x;
    private double y;
    public Point(double cx, double cy) {
        x=cx;
        y=cy;
    }
    public Point() {
        this(0,0);
    }
    double cordx(){
        return x;
    }
    double cordy(){
        return y;
    }
    public void move(Wektor v){
        x+=v.dx;
        y+=v.dy;
    }
    public void rotate(Point p, double ang){
        double newX;
        double rndCos = Math.round(Math.cos(ang*Math.PI/180)*100);
        double rndSin = Math.round(Math.sin(ang*Math.PI/180)*100);
        newX = p.cordx() + (x - p.cordx()) * rndCos/100 - (p.cordy() - y) * rndSin/100;
        y = p.cordy() + (y - p.cordy()) * rndCos/100 + (x - p.cordx()) * rndSin/100;
        x=newX;
    }
    public void symmetry(Prosta p){
        Prosta perpPr = new Prosta(-p.b,p.a,x*p.b - y*p.a);
        Point mid = Prosta.intersection(p, perpPr);
        Wektor perpDir = new Wektor(mid.x-x, mid.y-y);
        mid.move(perpDir);
        x = mid.x;
        y = mid.y;
    }
    public String toString(){
        return "Point ("+x+"; "+y+")";
    }
}
