package DonationResultGenerator.Main;

import DonationResultGenerator.Resources.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultWindow extends JFrame implements ActionListener {
    //Final Static Variables
    private final static String TITLE = "Result Window";
    private final static Font DISFONT = GlobalVariables.FONT,
            OUTFONT=new Font(GlobalVariables.FONTNAME, Font.PLAIN, 12);
    //UI Variables
    private JTextField display;
    private JTextArea output;
    private boolean outB = false;
    private JButton outButton;
    private String outString;

    //Constructors
    ResultWindow(Frame parentFrame) {
        makeFrame(parentFrame);
        display = new JTextField("");
        display.setEditable(false);
        display.setFont(DISFONT);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        add(display);
        outString = "";
        output = new JTextArea(outString);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setFont(OUTFONT);
        output.setBackground(GlobalVariables.B);
        output.setForeground(GlobalVariables.W);
    }//ResultWindow Default

    ResultWindow(String displayString, String outString, Frame parentFrame) {
        makeFrame(parentFrame);
        display = new JTextField(displayString);
        display.setEditable(false);
        display.setFont(DISFONT);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        add(display);
        this.outString = outString;
        output = new JTextArea(outString);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setFont(OUTFONT);
        output.setBackground(GlobalVariables.B);
        output.setForeground(GlobalVariables.W);
        add(output);
    }//ResultWindow Full w/o Button

    ResultWindow(String displayString, String outString, Frame parentFrame, boolean outB) {
        makeFrame(parentFrame);
        display = new JTextField(displayString);
        display.setEditable(false);
        display.setFont(DISFONT);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        add(display);
        this.outString = outString;
        if (outB) {
            this.outB = outB;
            outButton = new JButton("Copy Command");
            outButton.setFont(DISFONT);
            outButton.setBackground(Color.BLACK);
            outButton.setForeground(GlobalVariables.B);
            outButton.addActionListener(this);
            add(outButton);
        } else {
            output = new JTextArea(outString);
            output.setEditable(false);
            output.setLineWrap(true);
            output.setFont(OUTFONT);
            output.setBackground(GlobalVariables.B);
            output.setForeground(GlobalVariables.W);
            add(output);
        }
    }//ResultWindow Full w/ Button

    private void makeFrame(Frame parentFrame) {
        setTitle(TITLE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(parentFrame.getSize());
        setLocation(parentFrame.getLocation());
        setResizable(false);
        setLayout(new GridLayout(1, 2, 5, 5));
        setBackground(GlobalVariables.B);
    }

    //Inherited Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == outButton) {
            StringSelection out = new StringSelection(outString);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(out, out);
            dispose();
        }
    }

    //Accessors
    public String getDisplayString() {
        return display.getText();
    }//getDisplayString

    //Mutators
    public void setDisplayString(String displayString) {
        display.setText(displayString);
    }//setDisplayString

    public String getOutputString() {
        return output.getText();
    }//getOutputString

    public void setOutputString(String outString) {
        this.outString = outString;
        if (!outB)
            output.setText(outString);
    }//setOutput

    public void reveal() {
        setVisible(true);
    }

    public void conceal() {
        setVisible(false);
    }

    public void refresh() {
        conceal();
        repaint();
        reveal();
    }
}
