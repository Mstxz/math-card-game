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

import GUI.Component.*;
import GUI.Component.PopupMenu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Component.PopupMenu;
import utils.CustomScrollBarUI;
import utils.ResourceLoader;
import utils.SharedResource;

public class DeckCreatorPage extends Page implements ActionListener {
    private TempDeckZone paLeft;
    private JPanel paRight;
    private JLabel title;
    private JPanel deckShow;
    private JPanel deckOption;
    private JButton saveButton;
    private JButton createButton;
    private JComboBox deckNameField;
    private PopupMenu popupMenu;
    private ArrayList<CardButton> cardButtonArrayList;
    private String name;

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
        paLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        paLeft.setOpaque(false);


        /*เปิดแถบเลื่อน

        cardScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cardScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
        //ปิดแถบเลื่อน
        JScrollPane cardScrollPane = new JScrollPane(paLeft);
        cardScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cardScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        cardScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        cardScrollPane.setOpaque(false);
        cardScrollPane.getViewport().setOpaque(false);
        deckOption = new JPanel(new FlowLayout());
        deckOption.setPreferredSize(new Dimension(300,100));

        saveButton = new JButton("Save");
        createButton = new JButton(ResourceLoader.loadPicture("assets/Component/CreateButton.png",220,65));
        //createButton.setPreferredSize(new Dimension(100, 100));
        saveButton.setPreferredSize(new Dimension(220, 65));
        
        popupMenu = new PopupMenu();
        PopupItem.menu = popupMenu;
        JPanel PopupMenuPanel = new JPanel(new FlowLayout());
        PopupMenuPanel.add(popupMenu);

        //deckNameField = new JComboBox<>(options);
        //deckNameField.setRenderer(new ColoredComboBoxRenderer());
        //deckNameField.setBorder(BorderFactory.createTitledBorder("Your Decks"));
        //deckNameField.setBackground(SharedResource.SIAMESE_BRIGHT);
        //deckNameField.setPreferredSize(new Dimension(100, 40));
        //deckNameField.setBorder(BorderFactory.createLineBorder(SharedResource.SIAMESE_BASE, 1, true));

        deckShow = new JPanel();
        deckShow.setLayout(new BorderLayout(0,10));
        deckShow.add(cardScrollPane,BorderLayout.CENTER);
        deckShow.add(deckOption,BorderLayout.SOUTH);
        deckOption.add(saveButton,BorderLayout.WEST);
        deckOption.add(createButton,BorderLayout.EAST);
        deckShow.add(PopupMenuPanel,BorderLayout.NORTH);
        deckShow.setBackground(SharedResource.SIAMESE_LIGHT);

        //ปิดการแสดงผล
        cardScrollPane.setOpaque(false);
        cardScrollPane.getViewport().setOpaque(false);
        paLeft.setOpaque(false);
        popupMenu.setOpaque(false);
        saveButton.setOpaque(false);
        PopupMenuPanel.setOpaque(false);
        deckOption.setOpaque(false);
        createButton.setContentAreaFilled(false);
        createButton.setBorderPainted(false);
        createButton.setFocusPainted(false);

        //ปิดขอบ
        deckShow.setBorder(BorderFactory.createEmptyBorder()); 
        cardScrollPane.setBorder(BorderFactory.createEmptyBorder());
        paLeft.setBorder(BorderFactory.createEmptyBorder());
        popupMenu.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setBorder(BorderFactory.createEmptyBorder());

        mainPanel.add(deckShow, BorderLayout.WEST);

        paRight = new JPanel(new FlowLayout(FlowLayout.LEFT,25,25)) {
            @Override
            protected void paintComponent(Graphics g) {
                int column = getWidth() / 225;
                int row = Math.ceilDiv(getComponentCount(),column);
                this.setPreferredSize(new Dimension(300,row * (getComponent(0).getHeight() + 25) + 10));
                super.paintComponent(g);
            }
        };
        cardButtonArrayList = new ArrayList<CardButton>();
        loadButton();
        paRight.setBackground(SharedResource.SIAMESE_LIGHT); 
        JScrollPane scrollPane = new JScrollPane(paRight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        saveButton.addActionListener(this);
        createButton.addActionListener(this);
        PopupItem.deckZone = paLeft;
    }
    public Dimension calculateDimension(){
        int column = paRight.getWidth() / 225;
        int row = Math.ceilDiv(paRight.getComponentCount(),column);
        return new Dimension(300,row * (paRight.getComponent(0).getHeight() + 25) + 10);
    }

    private void loadButton(){
        ArrayList<String> fileString = ResourceLoader.readFile("Gameplay/Cards/CardList.txt");
        for (int i = 0;i<fileString.size();i++){
            CardButton tmp = new CardButton(fileString.get(i),paLeft);
            paRight.add(tmp);
            cardButtonArrayList.add(tmp);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)){
            if (!popupMenu.getCurrentDeck().getFileName().equals(popupMenu.getCurrentName())){
                File f = new File("Assets/"+ popupMenu.getCurrentDeck().getFileName() +".deck");
                if (f.delete()) {
                    System.out.println("Delete old deck");
                }
            }
            File f = new File("Assets/"+ popupMenu.getCurrentName() +".deck");
            System.out.println("Have File");
            try {
                FileOutputStream out = new FileOutputStream(f);
                CardLabel[] s = new CardLabel[paLeft.getAllCardLabel().size()];
                s = paLeft.getAllCardLabel().toArray(s);
                for (int i = 0;i<s.length;i++){
                    String className = s[i].getCard().getClass().getSimpleName();
                    String text = className+" "+s[i].getCardType().toString()+" "+s[i].getAmount();
                    if (className.equals("Plus") || className.equals("Minus")){
                        text = s[i].getName()+" "+s[i].getCardType().toString()+" "+s[i].getAmount();
                    }
                    if (i!=s.length-1){
                        text+="\n";
                    }
                    System.out.println("Have Text");
                    System.out.println(text);
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
            popupMenu.setUpItem();
            System.out.println("Write");
        }
        else if (e.getSource().equals(createButton)) {
            String newName = JOptionPane.showInputDialog(null, "Create Deck", name);
//            if (newName != null && !newName.trim().isEmpty()) {
//                PopupMenu.items.add(new PopupItem(newName));
//                popupMenu.updateMenuPanel();
//                popupMenu.updateMainButton(newName);
//            }
        }
    }

    public static void main(String[] args) {
        new DeckCreatorPage();
    }
}