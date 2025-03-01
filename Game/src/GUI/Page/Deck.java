package GUI.Page;

import java.awt.*;
import javax.swing.*;

public class Deck {
    private JFrame fr;
    private JPanel paLeft;
    private JPanel paRight;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JButton B1;
    private JButton B2;
    private JButton B3;
    private JButton B4;
    private JButton B5;
    private JButton B6;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JButton b11;
    private JButton b12;
    private JButton b13;
    private JButton b14;
    private JButton b15;
    private JButton b16;

    public Deck() {
        fr = new JFrame();
        paLeft = new JPanel();
        paRight = new JPanel();

        // Initialize text fields
        t1 = new JTextField("Test");
        t2 = new JTextField();
        t3 = new JTextField();

        // Initialize buttons
        B1 = new JButton("Button 1");
        B2 = new JButton("Button 2");
        B3 = new JButton("Button 3");
        B4 = new JButton("Button 4");
        B5 = new JButton("Button 5");
        B6 = new JButton("Button 6");
        b1 = new JButton("Button R1");
        b2 = new JButton("Button R2");
        b3 = new JButton("Button R3");
        b4 = new JButton("Button R4");
        b5 = new JButton("Button R5");
        b6 = new JButton("Button R6");
        b7 = new JButton("Button R7");
        b8 = new JButton("Button R8");
        b9 = new JButton("Button R9");
        b10 = new JButton("Button R10");
        b11 = new JButton("Button R11");
        b12 = new JButton("Button R12");
        b13 = new JButton("Button R13");
        b14 = new JButton("Button R14");
        b15 = new JButton("Button R15");
        b16 = new JButton("Button R16");

        // Set up left panel layout and add buttons
        paLeft.setLayout(new GridLayout(3, 2));
        paLeft.add(B1);
        paLeft.add(B2);
        paLeft.add(B3);
        paLeft.add(B4);
        paLeft.add(B5);
        paLeft.add(B6);

        // Set up right panel layout and add buttons
        paRight.setLayout(new GridLayout(4, 4));
        paRight.add(b1);
        paRight.add(b2);
        paRight.add(b3);
        paRight.add(b4);
        paRight.add(b5);
        paRight.add(b6);
        paRight.add(b7);
        paRight.add(b8);
        paRight.add(b9);
        paRight.add(b10);
        paRight.add(b11);
        paRight.add(b12);
        paRight.add(b13);
        paRight.add(b14);
        paRight.add(b15);
        paRight.add(b16);

        // Set up frame layout using GridBagLayout
        fr.setTitle("Deck-Test");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add left panel (30% width) and make buttons expand
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0; // Make the left panel expand vertically as well
        gbc.fill = GridBagConstraints.BOTH; // Allow the buttons to expand fully
        gbc.insets = new Insets(10, 10, 10, 10); // 10px padding on all sides
        fr.add(paLeft, gbc);

        // Add right panel (70% width) and make buttons expand
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0; // Make the right panel expand vertically as well
        gbc.fill = GridBagConstraints.BOTH; // Allow the buttons to expand fully
        gbc.insets = new Insets(10, 30, 10, 10); // Double the left padding (30px) between paLeft and paRight
        fr.add(paRight, gbc);

        // Set frame size and visibility
        fr.setSize(1920, 1080);
        fr.setVisible(true);
    }

    private static int getCols() {
        return 3;
    }

    public static void main(String[] args) {
        new Deck();
    }
}
