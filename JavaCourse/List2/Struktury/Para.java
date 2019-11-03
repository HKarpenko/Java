/**
 * Ta klasa reprezentuje pary typu klucz-wartosc, zawiera ona metody toString i equal
 * oraz getter i setter do wartosci prywatnej
 * @author Heorhii Karpenko
 * @version 1.0*/
package Struktury;

public class Para {
    public final String klucz;
    private double wart;
    public Para(String _klucz, double _wart) throws RuntimeException{
        if(_klucz == null || _klucz == "")
        /**@exception NullPointerException Nie mozna utworzyc pary z kluczem null lub pustym String'iem*/
            throw new NullPointerException("Nie mozna utworzyc pary z pustym kluczem");
        klucz=_klucz;
        wart=_wart;
    }
    public double getWart() {
        return wart;
    }
    public void setWart(double _wart){
        wart = _wart;
    }
    @Override
    public String toString(){
        return "Para: ["+klucz+", "+wart+"]";
    }
    public boolean equals(Object o){
        if(klucz==((Para)o).klucz)
            return true;
        return false;
    }
}
