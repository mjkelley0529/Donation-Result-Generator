package DonationResultGenerator.Main;

import DonationResultGenerator.Resources.GlobalVariables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
    private ResultWindow runMinecraftLogic() {
        //Variables
        final String[][] OUTCOMMANDS = new String[3][10];
        //Bad Commands
        OUTCOMMANDS[0][0] = "/effect give @s slowness 20 1 true";
        OUTCOMMANDS[0][1] = "/effect give @s blindness 20 1 true";
        OUTCOMMANDS[0][2] = "/effect give @s poison 5 2 true";
        OUTCOMMANDS[0][3] = "/summon zombie ~3 ~ ~3 {HandItems:[{Count:1,id:diamond_sword},{}]," +
                "ArmorItems:[{},{},{Count:1,id:diamond_chestplate},{}],CustomName:\"\\\"Flying Zamboo\\\"\"," +
                "HandDropChances:[0.1f,0.0f],ArmorDropChances:[0.0f,0.0f,0.1f,0.0f]}";
        OUTCOMMANDS[0][4] = "/summon zombie ~-3 ~ ~-3 {HandItems:[{Count:1,id:diamond_sword},{}]," +
                "ArmorItems:[{},{},{Count:1,id:diamond_chestplate},{}],CustomName:\"\\\"Flying Zamboo\\\"\"," +
                "HandDropChances:[0.1f,0.0f],ArmorDropChances:[0.0f,0.0f,0.1f,0.0f]}";
        OUTCOMMANDS[0][5] = "/summon creeper ~3 ~1 ~3 {powered:1,CustomName:\"\\\"DEAD\\\"\"}";
        OUTCOMMANDS[0][6] = "/summon creeper ~-3 ~1 ~-3 {powered:1,CustomName:\"\\\"DEAD\\\"\"}";
        OUTCOMMANDS[0][7] = "/summon creeper ~-3 ~1 ~-3 {Passengers:[{id:creeper,powered:1,ActiveEffects:[{Id:1,Amplifier:0,Duration:999999}]}]}";
        OUTCOMMANDS[0][8] = "/summon creeper ~3 ~1 ~3 {Passengers:[{id:creeper,powered:1,ActiveEffects:[{Id:1,Amplifier:0,Duration:999999}]}]}";
        OUTCOMMANDS[0][9] = "/kill @p";
        //Good Commands
        OUTCOMMANDS[1][0] = "/give @s coal_ore{display:{Name:\"\\\"Common Donator Ore\\\"\"," +
                "Lore:[\"\\\"Just plain common ore. Should break.\\\"\"]}} 16";
        OUTCOMMANDS[1][1] = "/give @s iron_ore{display:{Name:\"\\\"Uncommon Donator Ore\\\"\"," +
                "Lore:[\"\\\"Better than common ore. But not by a lot.\\\"\"]}} 8";
        OUTCOMMANDS[1][2] = "/give @s lapis_ore{display:{Name:\"\\\"Rare Donator Ore\\\"\"," +
                "Lore:[\"\\\"Rare Donator Ore? You should feel special.\\\"\"]}} 4";
        OUTCOMMANDS[1][3] = "/give @s redstone_ore{display:{Name:\"\\\"Rare Donator Ore\\\"\"," +
                "Lore:[\"\\\"Rare Donator Ore? You should feel special.\\\"\"]}} 4";
        OUTCOMMANDS[1][4] = "/give @s gold_ore{display:{Name:\"\\\"Rare Donator Ore\\\"\"," +
                "Lore:[\"\\\"Rare Donator Ore? You should feel special.\\\"\"]}} 4";
        OUTCOMMANDS[1][5] = "/give @s diamond_ore{display:{Name:\"\\\"Mythic Donator Ore\\\"\"," +
                "Lore:[\"\\\"MYTHIC ORE!!!! ALL HAIL THE GOD OF RNG!!!!\\\"\"]}} 2";
        OUTCOMMANDS[1][6] = "/give @s nether_quartz_ore{display:{Name:\"\\\"Mythic Donator Ore\\\"\"," +
                "Lore:[\"\\\"MYTHIC ORE!!!! ALL HAIL THE GOD OF RNG!!!!\\\"\"]}} 2";
        OUTCOMMANDS[1][7] = "/give @s diamond_sword{display:{Name:\"\\\"Sword of the Donator\\\"\"," +
                "Lore:[\"\\\"A powerful weapon forged from the magic of RNG.\\\"\"]}," +
                "Enchantments:[{id:sharpness,lvl:5},{id:looting,lvl:3}," +
                "{id:sweeping,lvl:3},{id:unbreaking,lvl:3},{id:mending,lvl:1}]} 1";
        OUTCOMMANDS[1][8] = "/give @s shield{display:{Name:\"\\\"Shield of the Donator\\\"\"," +
                "Lore:[\"\\\"A powerful shield forged from the Magic of RNG.\\\"\"]}," +
                "Enchantments:[{id:unbreaking,lvl:3},{id:mending,lvl:1}]," +
                "BlockEntityTag:{Base:15,Patterns:[{Pattern:\"sku\",Color:10}]}} 1";
        OUTCOMMANDS[1][9] = "/effect give @s health_boost 100000 1 true";
        OUTCOMMANDS[2][0] = "/give @s diamond_block 64";
        double dA=1;
        try {
            dA=Double.parseDouble(input.getText());
        } catch(Exception e) {
            dA=1;
        }
        int[] out = {0, 0};
        Random random = new Random();
        //Logic
        int i=1, randInt=0;
        while(dA>=i) {
            randInt=random.nextInt(i);
            i++;
        }
        if(randInt<=2) {
            out[0]=1;
        } else {
            out[0]=0;
        }
        randInt=random.nextInt(100)+1;
        if(out[0]==0) {
            if (randInt <= 40) {
                out[1] = 0;
            } else if(randInt>40&&randInt<=60) {
                out[1]=1;
            } else if(randInt>60&&randInt<=70) {
                out[1]=2;
            } else if(randInt>70&&randInt<=80) {
                out[1]=3;
            } else if(randInt>80&&randInt<=90) {
                out[1]=4;
            } else if(randInt>90&&randInt<=93) {
                out[1]=5;
            } else if(randInt>93&&randInt<=96) {
                out[1]=6;
            } else if(randInt==97) {
                out[1]=7;
            } else if(randInt==98) {
                out[1]=8;
            } else if(randInt>=99) {
                out[1]=9;
            }
        } else {
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
                out[1]=9;
            }
        }
        //Special Cases
        if(dA==69) {
            out[0]=2;
            out[1]=0;
        }
        //ShowResults
        return new ResultWindow("Execute this command:", OUTCOMMANDS[out[0]][out[1]], this, true);
    }//runMinecraftLogic
    private ResultWindow runOverwatchLogic() {
        return new ResultWindow(this);
    }//runOverwatchLogic
    private ResultWindow runKSPLogic() {
        return new ResultWindow(this);
    }//runKSPLogic
    //Utility Methods
    private <T> void print(T printString) {
        System.out.println(printString);
    }//print
    public static String getJTitle() {
        return TITLE+" "+VERID;
    }//getTitle
    //Inherited Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == b) {
            ResultWindow rw = null;
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
            try {
                rw.reveal();
            } catch (NullPointerException e) {
                e.printStackTrace();
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
