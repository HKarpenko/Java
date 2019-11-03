/**
 * To jest klasa abstrakcyjna opisujaca zbior par: klucz-wartosc
 * @author Heorhii Karpenko
 * @version 1.0*/
package Struktury;

public abstract class Zbior {
    public abstract Para szukaj (String k) throws Exception;

    public abstract void wstaw (Para p) throws Exception;

    public abstract double czytaj (String k) throws Exception;

    public abstract void ustaw (Para p) throws Exception;

    public abstract void czysc ();

    public abstract int ile ();
}
