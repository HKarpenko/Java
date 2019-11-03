package Java1App;

//java -Dfile.encoding=cp852 Java1App.Java1Main 49 abc -7

public class Java1Main {
    private static String[] rzymskie = {
            "M", "CM", "D", "CD", "C","XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };
    private static int[] arabskie = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    public static void main(String args[]){
        for(String arg : args) {
            try {
                System.out.println(arg+" "+IntToRom(Integer.valueOf(arg)));
            }
            catch(NumberFormatException exc) {
                System.err.println("Invalid argument specified: "+exc);
            }
            catch(IllegalArgumentException exc){
                System.err.println(exc);
            }
        }
    }
    public static String IntToRom(int num) {
        if(num<=0 || num>=4000)
            throw new IllegalArgumentException("liczba " + num + " spoza zakresu 1-3999");
        String RomForm = "";
        for (int i = 0; i < rzymskie.length && num != 0; i++)
            while (num >= arabskie[i]) {
                num -= arabskie[i];
                RomForm += rzymskie[i];
            }
        return RomForm;
    }
}
