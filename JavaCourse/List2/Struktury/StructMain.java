/**@author Heorhii Karpenko*/
package Struktury;

public class StructMain {
    public static void main(String[] args){
        /** metoda ma szukać pary z określonym kluczem */
            Para p1 = new Para("a", 1);
            Para p2 = new Para("b", 2);
        try {
            System.out.println("Teraz nie da sie stworzyc pary:");
            Para p3 = new Para("", 3);
        }
        catch (Exception exc){
            System.out.println(exc);
        }
        try {
            Zbior z1 = new ZbiorNaTablicy(2);
            System.out.println("Tworzymy pusty zbior, ma on " + z1.ile() + " elementow");
            System.out.println("Wstawiamy "+p1);
            z1.wstaw(p1);
            System.out.println("Wstawiamy "+p2);
            z1.wstaw(p2);
            System.out.println("Teraz zbior ma " + z1.ile() + " elementy");
            try {
                z1.wstaw(p1);
            } catch (Exception exc){
                System.out.println(exc);
            }
            System.out.println("Nie daje sie wstawic tego samego elementu do juz pelengo zbioru");
            z1.ustaw(p1);
            System.out.println("Jednak mozemy sprobowac ustawic");
            System.out.println("Element o kluczu 'a' zawiera: "+z1.czytaj("a")+"\tw parze "+z1.szukaj("a"));
            System.out.println("Czyszczymy zbior");
            z1.czysc();
            System.out.println("Zbior teraz zawiera: "+z1.ile()+" elementow");
            System.out.println("Stworzymy taki samy zbior na tablicy dynamicznej");
            Zbior z2 = new ZbiorNaTablicyDynamicznej(2);
            z2.wstaw(p1);
            z2.wstaw(p2);
            System.out.println("Sprobujemy wstawic nowy element do juz pelengo zbioru");
            z2.wstaw(new Para("c",3));
            System.out.println("Nowy element o kluczu 'c' zawiera: "+z2.czytaj("c")+"\tw parze "+z2.szukaj("c"));
        }
        catch (Exception exc){
            System.out.println(exc);
        }
    }
}
