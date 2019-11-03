/**
 * Ta klasa reprezentuje zbior par: klucz-wartosc; Zawiera on metody do wstawiania,
 * poszukiwania znaczen po kluczu, czyszczenia zbioru oraz wyznczenia mocy zbioru
 * @author Heorhii Karpenko
 * @version 1.0*/
package Struktury;

public class ZbiorNaTablicy extends Zbior {
    protected Para[] arrKeyVal;
    protected int ilosc;
    public ZbiorNaTablicy(int len) throws NegativeArraySizeException{
        if(len<2)
        /**@exception NegativeArraySizeException Bez sensu jest tworzenie zbioru o 1 lub mniej elementach*/
            throw new NegativeArraySizeException("Rozmiar nie moze byc mniejszy 2");
        arrKeyVal=new Para[len];
        ilosc=0;
    }
    public Para szukaj(String k) throws Exception {
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == k)
                return arrKeyVal[i];
        return null;
    }
    public void wstaw (Para p) throws Exception{
        if(ilosc==arrKeyVal.length)
        /**@exception IllegalStateException Proba wstawic nowy element do juz pelnego zbioru*/
            throw new IllegalStateException();
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == p.klucz)
                return;
        arrKeyVal[ilosc++]=p;
    }
    public double czytaj (String k) throws Exception{
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == k)
                return arrKeyVal[i].getWart();
        /**@exception RuntimeException Proba zwrocic wartosc, klucza ktorej nie ma w zbiorze*/
        throw new RuntimeException("Nie znaleziono znaczenia");
    }
    public void ustaw (Para p) throws Exception{
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == p.klucz) {
                arrKeyVal[i] = p;
                return;
            }
        if(ilosc==arrKeyVal.length)
        /**@exception IllegalStateException Proba dodac nowy element do juz pelnego zbioru*/
            throw new IllegalStateException();
        arrKeyVal[ilosc++]=p;
    }
    public void czysc (){
        for(int i=0; i<ilosc; i++)
            arrKeyVal[i]=null;
        ilosc=0;
    }
    public int ile () {
        return ilosc;
    }
}
