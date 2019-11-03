import Geometria.*;

public class JavaGeoTest {
    public static void main(String[] args){
        Point p1 = new Point(1,1);
        System.out.println("Pozatkowy punkt: "+p1);
        Wektor w1 = new Wektor(1,0);
        p1.move(w1);
        System.out.println("Punkt po przesunieciu na wektor (1,0): "+p1);
        p1.rotate(new Point(0,0),180);
        System.out.println("Punkt po obracaniu na kt 180 wokol (0,0): "+p1);
        p1.symmetry(new Prosta(1,1,0));
        System.out.println("Punkt po symetrii wzgledem y=-x: "+p1);
        Wektor w2 = new Wektor(-5,10);
        Wektor w3 = Wektor.add(w1,w2);
        System.out.println("dodajemy wektory w1("+w1.dx+";"+w1.dy+") i w2("+w2.dx+";"+w2.dy+")");
        System.out.println("Wynik w3("+w3.dx+";"+w3.dy+")");
        Prosta pr1 = new Prosta(-1,1,0); //y=x
        Prosta pr2 = Prosta.przesun(pr1,new Wektor(0,1)); //y=4
        System.out.println("Przesuwamy prosta y="+(-pr1.a)+"*x na wektor w(0,1)");
        System.out.println("Wynik y="+(-pr2.a)+"*x + "+(-pr2.c));
        System.out.println("Czy proste sa rownolegle? "+Prosta.parallel(pr1,pr2));
        Prosta pr3 = new Prosta(1,1,0); //y=-x
        System.out.println("Czy y=x i y=-x sa prostopadle? "+Prosta.perpendicular(pr3,pr1));
        System.out.println("Punkt ich przeciecia: "+ Prosta.intersection(pr1,pr3));
    }
}
