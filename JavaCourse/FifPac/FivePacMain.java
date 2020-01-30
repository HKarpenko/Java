package FifPac;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class FivePacMain {
    public static void main(String[] args){
        GregorianCalendar gc = new GregorianCalendar();
        for(String s : TimeZone.getAvailableIDs())
            System.out.println(s);
        System.out.println(TimeZone.getDefault());
    }
}

