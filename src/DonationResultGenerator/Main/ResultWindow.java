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
            OUTFONT=GlobalVariables.FONT;
    //UI Variables
    private JLabel display, output;
    private JPanel pane=new JPanel();
    private boolean outB = false;
    private JButton outButton;
    private String outString;

    //Constructors
    ResultWindow(Frame parentFrame) {
        makeFrame(parentFrame);
        display = new JLabel("");
        display.setFont(DISFONT);
        display.setHorizontalAlignment(0);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        pane.add(display);
        outString = "";
        output = new JLabel(outString);
        output.setFont(OUTFONT);
        output.setHorizontalAlignment(0);
        output.setBackground(GlobalVariables.B);
        output.setForeground(GlobalVariables.W);
    }//ResultWindow Default
    ResultWindow(String displayString, String outString, Frame parentFrame) {
        makeFrame(parentFrame);
        display = new JLabel(displayString);
        display.setFont(DISFONT);
        display.setHorizontalAlignment(0);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        pane.add(display);
        this.outString = outString;
        output = new JLabel(outString);
        output.setFont(OUTFONT);
        output.setHorizontalAlignment(0);
        output.setBackground(GlobalVariables.B);
        output.setForeground(GlobalVariables.W);
        pane.add(output);
    }//ResultWindow Full w/o Button
    ResultWindow(String displayString, String outString, Frame parentFrame, boolean outB) {
        makeFrame(parentFrame);
        display = new JLabel(displayString);
        display.setFont(DISFONT);
        display.setHorizontalAlignment(0);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        pane.add(display);
        this.outString = outString;
        if (outB) {
            this.outB = outB;
            outButton = new JButton("Copy Command");
            outButton.setFont(DISFONT);
            outButton.setBackground(Color.BLACK);
            outButton.setForeground(GlobalVariables.B);
            outButton.addActionListener(this);
            pane.add(outButton);
        } else {
            output = new JLabel(outString);
            output.setFont(OUTFONT);
            output.setHorizontalAlignment(0);
            output.setBackground(GlobalVariables.B);
            output.setForeground(GlobalVariables.W);
            pane.add(output);
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
        pane.setBackground(GlobalVariables.B);
        add(pane);
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
