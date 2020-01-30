package FifPac;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AppMouseAdapter extends MouseAdapter {
    AppWindow appWin;
    public AppMouseAdapter(AppWindow aw){
        appWin = aw;
    }
    private Color chooseColor(){
        switch (appWin.colors.getSelectedCheckbox().getLabel()) {
            case "Red" : {
                return new Color(255,0,0);
            }
            case "Green" : {
                return new Color(0,255,0);
            }
            case "Blue" : {
                return new Color(0,0,255);
            }
            case "Black" : {
                return new Color(0,0,0);
            }
            case "Yellow" : {
                return new Color(255,255,0);
            }
        }
        return null;
    }
    public void mousePressed(MouseEvent me){
        appWin.mouseX = me.getX();
        appWin.mouseY = me.getY();
        if(appWin.newLinePoint1==null) {
            appWin.newLinePoint1 = new Point(appWin.mouseX, appWin.mouseY);
        }
        else if(appWin.newLinePoint2==null) {
            appWin.newLinePoint2 = new Point(appWin.mouseX, appWin.mouseY);
            appWin.lines.add(new Kreska(appWin.newLinePoint1, appWin.newLinePoint2, chooseColor()));
            appWin.newLinePoint1 = appWin.newLinePoint2 = null;
        }
        appWin.repaint();
    }

    public void mouseMoved(MouseEvent me){
        appWin.mouseX = me.getX();
        appWin.mouseY = me.getY();
        appWin.repaint();
    }
}
