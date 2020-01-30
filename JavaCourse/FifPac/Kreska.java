package FifPac;

import java.awt.*;

public class Kreska
{
    protected Point poczatek, koniec;
    public final Color kolor;
    public Kreska (Point pocz, Point kon, Color kol) {
        poczatek=pocz;
        koniec=kon;
        kolor=kol;
    }
    public void draw(Graphics g){
        g.setColor(kolor);
        g.drawLine((int)poczatek.getX(), (int)poczatek.getY(), (int)koniec.getX(), (int)koniec.getY());
    }
}
