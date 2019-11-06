package DonationResultGenerator.Main;

import javax.swing.*;
import java.awt.*;
import DonationResultGenerator.Resources.*;

public class ResultWindow extends JFrame {
    private final Font DISFONT=GlobalVariables.FONT,
            OUTFONT=new Font(GlobalVariables.FONTNAME, Font.PLAIN, 12);

    ResultWindow(String displayString, String outString, Frame parentFrame) {
        setTitle("Result Window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(parentFrame.getSize());
        setLocation(parentFrame.getLocation());
        setResizable(false);
        setLayout(new GridLayout(1,2,5,5));
        setBackground(GlobalVariables.B);
        JTextField display=new JTextField(displayString);
        display.setEditable(false);
        display.setFont(DISFONT);
        display.setBackground(GlobalVariables.B);
        display.setForeground(GlobalVariables.P);
        add(display);
        JTextArea output=new JTextArea(outString);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setFont(OUTFONT);
        output.setBackground(GlobalVariables.B);
        output.setForeground(GlobalVariables.W);
        add(output);
        setVisible(true);
    }
}
