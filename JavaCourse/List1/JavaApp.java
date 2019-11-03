import java.lang.*;

public class JavaApp {
    public static void main(String args[]){
        for(String arg : args) {
            try {
                int num = new Integer(arg);
                System.out.println(toPolish(num));
            }
            catch(NumberFormatException exc) {
                System.err.println("Wskazano nieprawiedłowy argument: "+exc);
            }
        }
    }
    private static String toPolish(int num){
        String numbers0To19[] = { "zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć",
            "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście",
            "osiemnaście", "dziewiętnaście"};
        String numbers20To90[] = {"dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
            "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String numbers100To900[] = {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
                "osiemset", "dziewięćset"};
        String numbers1000To900[] = {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
                "osiemset", "dziewięćset"};
        String thousands[] = {"tysiąc", "tysiące", "tysięcy"};
        String millions[] = {"milion", "miliony", "milionów"};
        String billions[] = {"miliard", "miliardy", "miliardów"};

        boolean sign = num>=0;
        num=Math.abs(num);
        if(num>=0 && num<=19)
            return sign ? numbers0To19[num] : "minus "+numbers0To19[num];
        else if(num<100) {
            if (num % 10 == 0)
                return sign ? numbers20To90[num / 10 - 2] : "minus "+numbers20To90[num / 10 - 2];
            else
                return sign ? numbers20To90[num/10-2]+" "+numbers0To19[num%10] :
                    "minus "+numbers20To90[num/10-2]+" "+numbers0To19[num%10];
        }
        else if(num<1000){
            if (num % 100 == 0)
                return sign ? numbers100To900[num / 100 - 1] : "minus "+numbers100To900[num / 100 - 1];
            else
                return sign ?  numbers100To900[num/100-1]+" "+toPolish(num%100) :
                    "minus "+ numbers100To900[num/100-1]+" "+toPolish(num%100);
        }
        else {
            int degree;
            String[] dim;
            if (num < 1_000_000) {
                degree = 1000;
                dim = thousands;
            } else if (num < 1_000_000_000) {
                degree = 1_000_000;
                dim = millions;
            } else {
                degree = 1_000_000_000;
                dim = billions;
            }
            int ind = getIndex(num / degree);
            String str = sign ? "" : "minus";
            if (num / degree == 1)
                str += dim[0];
            else
                str += toPolish(num / degree) + " " + dim[ind];
            if (num % degree == 0)
                return str;
            else
                return str + " " + toPolish(num % degree);
        }
    }
    private static int getIndex(int num){
        if (num==1)
            return 0;
        else if(num>9 && num<20)
            return 2;
        else{
            int det=num-(num/10)*10;
            return  (det >= 2 && det <= 4) ? 1 : 2;
        }
    }
}
