package FifPac;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class AppKeyAdapter extends KeyAdapter {
    AppWindow appWin;
    public AppKeyAdapter(AppWindow aw){
        appWin = aw;
    }
    public void keyTyped(KeyEvent ke){
        if(ke.getKeyChar()=='f' || ke.getKeyChar()=='F')
            appWin.lines.pollFirst();
        else if(ke.getKeyChar()=='b' || ke.getKeyChar()=='B' || ke.getKeyChar()=='l' || ke.getKeyChar()=='L')
            appWin.lines.pollLast();
        else if(ke.getKeyChar()=='\b')
            appWin.lines.clear();
        appWin.repaint();
    }
}
