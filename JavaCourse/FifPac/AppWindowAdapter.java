package FifPac;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AppWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we){
        System.exit(0 );
    }
}
