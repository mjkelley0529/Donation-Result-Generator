package DonationResultGenerator.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameChooser extends JFrame implements ActionListener, KeyListener {
    //Static Final Variables
    private static final String TITLE="Donation Result Generator";
    private static final String VERID="0.1";
    private static final String[] GAMELIST = {
            "Overwatch",
            "Minecraft",
            "Kerbal Space Program"
    };
    private static final String[] COLORSTRING = {
            "#FFFFFF",
            "#00FF00",
            "#7000AF"
    };
    //UI Variables
    private JButton b[]=new JButton[GAMELIST.length];
    private GridLayout frameLay=new GridLayout((int)Math.ceil(GAMELIST.length/2.0),(int)Math.ceil(GAMELIST.length/2.0),5,5);
    private Font font=new Font("Arial", Font.BOLD, 24);

    public GameChooser() {
        setTitle(TITLE+" "+VERID);
        int w=600;
        int h=(int)((w/16.0)*9.0);
        setSize(w, h);
        setResizable(false);
        setLayout(frameLay);
        for(int i=0;i<b.length;i++) {
            b[i] = new JButton(GAMELIST[i]);
            b[i].addActionListener(this);
            b[i].addKeyListener(this);
            b[i].setFocusable(false);
            b[i].setFont(font);
            b[i].setBackground(Color.BLACK);
            b[i].setForeground(Color.decode(COLORSTRING[i]));
            add(b[i]);
        }
        setAutoRequestFocus(true);
        addKeyListener(this);
        setVisible(true);
    }
    //Utility Methods
    private <T> void print(T inT) {
        System.out.println(inT);
    }
    //Main Method
    public static void main(String[] args) {
        new GameChooser();
    }
    //Inherited Methods
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<b.length;i++) {
            if (e.getSource() == b[i]) {
                //new DonationInputWindow(GAMELIST[i]);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        for(int i=1;i<=b.length;i++) {
            if (e.getKeyChar() == Character.forDigit(i, 10)) {
                b[i - 1].doClick();
                break;
            }
        }
    }
}
