package DonationResultGenerator.Main;

import DonationResultGenerator.Resources.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class DonationInputWindow extends JFrame implements ActionListener, KeyListener {
    //Final Variables
    private static final String TITLE="Donation Consequences";
    private static final String VERID="1.0";
    //Functional Variables
    private JPanel pane = new JPanel();
    private JPanel[] spacer = new JPanel[2];
    private JPanel[] containerPane = new JPanel[2];
    private JTextField display=new JTextField("Donation Amount: "),
            input=new JTextField();
    private JButton b=new JButton("OK");
    private GridLayout mainLay=new GridLayout(2,1,5,5),
            cont0Lay=new GridLayout(1,2,5,5),
            cont1Lay=new GridLayout(1,3,5,5);
    private Font FONT= GlobalVariables.FONT;
    private Color B=GlobalVariables.B,
            G=GlobalVariables.G,
            P=GlobalVariables.P,
            W=GlobalVariables.W;
    private String gameString = "";

    //Constructor
    public DonationInputWindow(String gameString) {
        this.gameString = gameString;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Fully Initialize All Variables
        pane.setFocusable(false);
        pane.setBackground(B);
        for(int i =0;i<spacer.length;i++){
            spacer[i]=new JPanel();
            spacer[i].setFocusable(false);
            spacer[i].setBackground(B);
        }
        for(int i=0;i<containerPane.length;i++){
            containerPane[i]=new JPanel();
            containerPane[i].setFocusable(false);
            containerPane[i].setBackground(B);
        }
        display.setFocusable(false);
        display.setEditable(false);
        display.setFont(FONT);
        display.setBackground(B);
        display.setForeground(P);
        input.addKeyListener(this);
        input.setFont(FONT);
        input.setBackground(B);
        input.setForeground(W);
        b.addActionListener(this);
        b.setFont(FONT);
        b.setBackground(B);
        b.setForeground(G);

        //Setup Window
        setTitle(getJTitle());
        setSize(400,150);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(B);
        //Window Layout
        pane.setLayout(mainLay);
        containerPane[0].setLayout(cont0Lay);
        containerPane[1].setLayout(cont1Lay);
        //Adding Panels/Inputs
        containerPane[0].add(display);
        containerPane[0].add(input);
        pane.add(containerPane[0]);
        containerPane[1].add(spacer[0]);
        containerPane[1].add(b);
        containerPane[1].add(spacer[1]);
        pane.add(containerPane[1]);
        add(pane);

        setVisible(true);
    }//DonationInputWindow
    //Game Logics
    private ResultWindow runMinecraftLogic() throws FileNotFoundException {
        //Variables
        String[][] outCommands = readFile("CommandList.txt");
        print(outCommands.length);
        double dA=1;
        try {
            dA=Double.parseDouble(input.getText());
        } catch(Exception e) {
            dA=1;
        }
        int[] out = {0, 0};
        Random random = new Random();
        //Logic
        int randInt=random.nextInt((int)dA);
        if(randInt<=2) {
            out[0]=1;
        } else {
            out[0]=0;
        }
        randInt=random.nextInt(100)+1;
        if (randInt <= 40) {
            out[1] = 0;
        } else if(randInt<=60) {
            out[1]=1;
        } else if(randInt<=70) {
            out[1]=2;
        } else if(randInt<=80) {
            out[1]=3;
        } else if(randInt<=90) {
            out[1]=4;
        } else if(randInt<=93) {
            out[1]=5;
        } else if(randInt<=96) {
            out[1]=6;
        } else if(randInt==97) {
            out[1]=7;
        } else if(randInt==98) {
            out[1]=8;
        } else if(randInt>=99) {
            out[1] = 9;
        }
        //Special Cases
        if(dA==69) {
            out[0]=2;
            out[1]=0;
        }
        //ShowResults
        return new ResultWindow("Execute this command:", outCommands[out[0]][out[1]], this, true);
    }//runMinecraftLogic
    private ResultWindow runOverwatchLogic() throws FileNotFoundException {
        String[][] HeroList=readFile("HeroList.txt");
        return new ResultWindow(this);
    }//runOverwatchLogic
    private ResultWindow runKSPLogic() {
        return new ResultWindow(this);
    }//runKSPLogic
    private String[][] readFile(String fileName) throws FileNotFoundException {
        FileReader inFile=new FileReader(new File("src/DonationResultGenerator/Resources/"+fileName));
        Scanner fileScan=new Scanner(inFile);
        int dimX=fileScan.nextInt();
        String outStrings[][]=new String [dimX][];
        fileScan.nextLine();
        for(int i=0;i<dimX;i++) {
            String inString="";
            int aIterations=0;
            if(fileScan.hasNextLine()) {
                inString=fileScan.nextLine();
            }
            if(inString.contains("<")&&inString.contains(">")) {
                aIterations=Integer.parseInt(inString.substring(inString.indexOf('%')+1));
                outStrings[i]=new String[aIterations];
            }
            for(int a=0;a<aIterations;a++) {
                inString=fileScan.nextLine();
                outStrings[i][a] = inString;
            }
        }
        return outStrings;
    }
    //Utility Methods
    private <T> void print(T printString) {
        System.out.println(printString);
    }//print
    private <T> void print(T printString, boolean error) {
        if(error) {
            System.err.println(printString);
        } else {
            System.out.println(printString);
        }
    }//print
    private void print(String[][] printStrings) {
        for(int i=0;i<printStrings.length;i++) {
            for(int a=0;a<printStrings[i].length;a++) {
                print(printStrings[i][a]+" "+i+" "+a);
            }
        }
    }
    public static String getJTitle() {
        return TITLE+" "+VERID;
    }//getTitle
    //Inherited Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == b) {
            ResultWindow rw = null;
            try {
                switch (gameString) {
                    case "Minecraft":
                        rw = runMinecraftLogic();
                        break;
                    case "Overwatch":
                        rw = runOverwatchLogic();
                        break;
                    case "Kerbal Space Program":
                        rw = runKSPLogic();
                        break;
                    default:
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(rw!=null) {
                rw.reveal();
            }
            input.setText("");
        }
    }//actionPerformed
    @Override
    public void keyTyped(KeyEvent e) {
    }//keyTyped
    @Override
    public void keyPressed(KeyEvent e) {
    }//keyPressed
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==10) {
            b.doClick();
        }
    }//keyReleased
}//class
