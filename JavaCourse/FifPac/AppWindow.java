package FifPac;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AppWindow extends Applet implements ItemListener {
    int mouseX = 30, mouseY = 30;
    Checkbox red, green, blue, black, yellow;
    CheckboxGroup colors;
    java.util.LinkedList<Kreska> lines = new java.util.LinkedList<Kreska>();
    Point newLinePoint1=null, newLinePoint2=null;
    public void init(){
        setLayout(new BorderLayout());

        this.setFocusable(true);
        setSize(1000,600);
        addKeyListener(new AppKeyAdapter(this));
        addMouseListener(new AppMouseAdapter(this));
        addMouseMotionListener(new AppMouseAdapter(this));

        colors = new CheckboxGroup();
        red = new Checkbox("Red", colors,true);
        green = new Checkbox("Green", colors,false);
        blue = new Checkbox("Blue", colors,false);
        black = new Checkbox("Black", colors,false);
        yellow = new Checkbox("Yellow", colors,false);
        Panel cheksPan = new Panel();
        cheksPan.setLayout(new GridLayout(5,1));
        cheksPan.add(red);
        cheksPan.add(blue);
        cheksPan.add(black);
        cheksPan.add(green);
        cheksPan.add(yellow);
        add(cheksPan,BorderLayout.WEST);
        red.addItemListener(this);
        blue.addItemListener(this);
        black.addItemListener(this);
        green.addItemListener(this);
        yellow.addItemListener(this);
    }
    public void paint(Graphics g){
        if(newLinePoint1 != null) {
            new Kreska(newLinePoint1, new Point(mouseX, mouseY), new Color(133, 133, 133)).draw(g);
        }
        for(Kreska line : lines)
            line.draw(g);
    }
    public void itemStateChanged(ItemEvent ie){
        repaint();
        this.requestFocus();
    }
}