//Code เก่า
/*package GUI.Page;

import GUI.Component.CardButton;
import GUI.Component.CardLabel;
import GUI.Component.PopupMenu;
import GUI.Component.TempDeckZone;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.ResourceLoader;
import utils.SharedResource;

public class DeckCreatorPage extends Page implements ActionListener {
    private TempDeckZone paLeft;
    private JPanel paRight;
    private JLabel title;
    private JPanel deckShow;
    private JButton saveButton;
    private JComboBox<String> deckNameField;

    public DeckCreatorPage() {
        mainPanel.setLayout(new BorderLayout(20,0));
        mainPanel.setBorder(new EmptyBorder(10,80,10,80));
        // Title Label
        title = new JLabel("Your Deck");
        title.setFont(SharedResource.getFont48());
        title.setForeground(SharedResource.SIAMESE_DARK);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setPreferredSize(new Dimension(title.getWidth(),100));
        mainPanel.add(title, BorderLayout.NORTH);

        // Left Panel (3x2 grid with images)
        paLeft = new TempDeckZone();
        paLeft.setLayout(new GridLayout(3, 2, 10, 10));
        paLeft.setPreferredSize(new Dimension(500,1080));
        paLeft.setBackground(SharedResource.SIAMESE_LIGHT); 
        saveButton = new JButton("Save");

        PopupMenu deckNameField = new PopupMenu();
        //String[] options = { "Deck 1", "Deck 2", "Deck 3", "Create New" };
        //deckNameField = new JComboBox<>(options);
        //deckNameField.setRenderer(new ColoredComboBoxRenderer());
        //deckNameField.setBorder(BorderFactory.createTitledBorder("Your Decks"));
        //deckNameField.setBackground(SharedResource.SIAMESE_BRIGHT);
        //deckNameField.setPreferredSize(new Dimension(100, 40));
        //deckNameField.setBorder(BorderFactory.createLineBorder(SharedResource.SIAMESE_BASE, 1, true));

        deckShow = new JPanel();
        deckShow.setLayout(new BorderLayout(0,10));
        deckShow.setPreferredSize(new Dimension(500,1080));
        deckShow.add(paLeft,BorderLayout.CENTER);
        deckShow.add(saveButton,BorderLayout.SOUTH);
        deckShow.add(deckNameField,BorderLayout.NORTH);

        mainPanel.add(deckShow, BorderLayout.WEST);


        paRight = new JPanel(new FlowLayout(FlowLayout.LEFT,25,25));
        loadButton();
        paRight.setPreferredSize(new Dimension(300,3000));
        paRight.setBackground(SharedResource.SIAMESE_LIGHT); 
        JScrollPane scrollPane = new JScrollPane(paRight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1000,900));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        saveButton.addActionListener(this);
    }

    private void loadButton(){
        ArrayList<String> fileString = ResourceLoader.readFile("Gameplay/Cards/CardList.txt");
        for (int i = 0;i<fileString.size();i++){
            paRight.add(new CardButton(fileString.get(i),paLeft));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)){
            File f = new File("Assets/"+ deckNameField.getSelectedItem().toString() +".deck");
            System.out.println("Have File");
            try {
                FileOutputStream out = new FileOutputStream(f);
                CardLabel[] s = new CardLabel[paLeft.getAllCardLabel().size()];
                s = paLeft.getAllCardLabel().toArray(s);
                for (int i = 0;i<s.length;i++){
                    String text = s[i].getName()+" "+s[i].getAmount();
                    if (i!=s.length-1){
                        text+="\n";
                    }
                    System.out.println("Have Text");
                    for (int j = 0;j<text.toCharArray().length;j++){
                        try {
                            out.write((int)(text.charAt(j)));
                        }
                        catch (IOException ex){
                            System.out.println("IO Error");
                            return;
                        }
                    }
                    System.out.println("Write Text");
                }
            }
            catch (FileNotFoundException ex){
                System.out.println("FileNotFound Error");
                return;
            }
            System.out.println("Write");
        }
    }

    public static void main(String[] args) {
        new DeckCreatorPage();
    }
}
*/
package GUI.Page;

import GUI.Component.CardButton;
import GUI.Component.CardLabel;
import GUI.Component.ExitButton; //PopupMenu สำหรับเลือก Deck และแก้ไข ลบ
import GUI.Component.PopupMenu;
import GUI.Component.TempDeckZone;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import utils.ResourceLoader;
import utils.SharedResource;

public class DeckCreatorPage extends Page implements ActionListener {
    private TempDeckZone paLeft;
    private JPanel paRight;
    private JLabel title;
    private JPanel deckShow;
    private JButton saveButton;
    private JComboBox deckNameField;

    public DeckCreatorPage() {
        mainPanel.setLayout(new BorderLayout(20,0));
        mainPanel.setBorder(new EmptyBorder(10,80,80,80));
        // Title Label
        title = new JLabel("Your Deck");
        title.setFont(SharedResource.getFont48());
        title.setForeground(SharedResource.SIAMESE_DARK);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setPreferredSize(new Dimension(title.getWidth(),100));

        ExitButton exitButton = new ExitButton("MainMenu");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(exitButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Left Panel (3x2 grid with images)
        paLeft = new TempDeckZone();
        paLeft.setLayout(new FlowLayout());
        paLeft.setPreferredSize(new Dimension(300,1000));
        paLeft.setBackground(SharedResource.SIAMESE_LIGHT);

        JScrollPane cardScrollPane = new JScrollPane(paLeft);
        cardScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cardScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        saveButton = new JButton("Save");

        String[] options = { "Deck 1", "Deck 2", "Deck 3", "Create New" };
        PopupMenu PopupMenu = new PopupMenu();
        PopupMenu.setBackground(SharedResource.SIAMESE_LIGHT); 
        //deckNameField = new JComboBox<>(options);
        //deckNameField.setRenderer(new ColoredComboBoxRenderer());
        //deckNameField.setBorder(BorderFactory.createTitledBorder("Your Decks"));
        //deckNameField.setBackground(SharedResource.SIAMESE_BRIGHT);
        //deckNameField.setPreferredSize(new Dimension(100, 40));
        //deckNameField.setBorder(BorderFactory.createLineBorder(SharedResource.SIAMESE_BASE, 1, true));

        deckShow = new JPanel();
        deckShow.setLayout(new BorderLayout(0,10));
        deckShow.add(cardScrollPane,BorderLayout.CENTER);
        deckShow.add(saveButton,BorderLayout.SOUTH);
        deckShow.add(PopupMenu,BorderLayout.NORTH);

        mainPanel.add(deckShow, BorderLayout.WEST);


        paRight = new JPanel(new FlowLayout(FlowLayout.LEFT,25,25)) {
            @Override
            protected void paintComponent(Graphics g) {
                this.setPreferredSize(calculateDimension());
                super.paintComponent(g);
            }
        };
        loadButton();
        paRight.setBackground(SharedResource.SIAMESE_LIGHT); 
        JScrollPane scrollPane = new JScrollPane(paRight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        saveButton.addActionListener(this);
    }
    public Dimension calculateDimension(){
        int column = paRight.getWidth() / 225;
        int row = Math.ceilDiv(paRight.getComponentCount(),column);
        return new Dimension(300,row * (paRight.getComponent(0).getHeight() + 25) + 10);
    }

    private void loadButton(){
        ArrayList<String> fileString = ResourceLoader.readFile("Gameplay/Cards/CardList.txt");
        for (int i = 0;i<fileString.size();i++){
            paRight.add(new CardButton(fileString.get(i),paLeft));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)){
            File f = new File("Assets/"+ deckNameField.getSelectedItem().toString() +".deck");
            System.out.println("Have File");
            try {
                FileOutputStream out = new FileOutputStream(f);
                CardLabel[] s = new CardLabel[paLeft.getAllCardLabel().size()];
                s = paLeft.getAllCardLabel().toArray(s);
                for (int i = 0;i<s.length;i++){
                    String text = s[i].getName()+" "+s[i].getAmount();
                    if (i!=s.length-1){
                        text+="\n";
                    }
                    System.out.println("Have Text");
                    for (int j = 0;j<text.toCharArray().length;j++){
                        try {
                            out.write((int)(text.charAt(j)));
                        }
                        catch (IOException ex){
                            System.out.println("IO Error");
                            return;
                        }
                    }
                    System.out.println("Write Text");
                }
            }
            catch (FileNotFoundException ex){
                System.out.println("FileNotFound Error");
                return;
            }
            System.out.println("Write");
        }
    }

    public static void main(String[] args) {
        new DeckCreatorPage();
    }
}