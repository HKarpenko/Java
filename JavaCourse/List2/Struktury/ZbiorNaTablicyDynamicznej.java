/**
 * Ta klasa dziedziczy klase zbior, nadpisujac metody wstawiania, tak by sami rozszerzali tablice
 * @author Heorhii Karpenko
 * @version 1.0*/
package Struktury;

public class ZbiorNaTablicyDynamicznej extends ZbiorNaTablicy {
    public ZbiorNaTablicyDynamicznej(int len) throws NegativeArraySizeException{
        super(len);
    }
    public void wstaw (Para p) throws Exception{
        if(ilosc==arrKeyVal.length){
            Para[] newArr = new Para[ilosc*2];
            for(int i = 0;i<ilosc;i++){
                newArr[i]=arrKeyVal[i];
            }
            arrKeyVal=newArr;
        }
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == p.klucz)
                return;
        arrKeyVal[ilosc++]=p;
    }
    public void ustaw (Para p) throws Exception{
        for(int i=0; i<ilosc; i++)
            if(arrKeyVal[i].klucz == p.klucz) {
                arrKeyVal[i] = p;
                return;
            }
        if(ilosc==arrKeyVal.length){
            Para[] newArr = new Para[ilosc*2];
            for(int i = 0;i<ilosc;i++){
                newArr[i]=arrKeyVal[i];
            }
            arrKeyVal=newArr;
        }
        arrKeyVal[ilosc++]=p;
    }
}
