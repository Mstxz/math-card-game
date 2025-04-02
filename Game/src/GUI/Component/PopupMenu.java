package GUI.Component;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPanelUI;

import AudioPlayer.SFXPlayer;
import GUI.Page.DeckCreatorPage;
import Gameplay.Card;
import Gameplay.Deck;
import utils.ResourceLoader;
import utils.UIManager.CustomScrollBarUI;
import utils.UIManager.RoundPanelUI;
import utils.SharedResource;

public class PopupMenu extends JPanel {
    private JPanel mainButton;
    private JButton arrowLabel;
    private JTextField deckName;
    private JPanel menuPanel;
    private JPanel menuWrapper;
    public static ArrayList<PopupItem> items;
    public int selectedIndex;
    private boolean isMenuVisible = false;

    public PopupMenu() {
        setLayout(new BorderLayout());

        items = new ArrayList<>();


        //items.add(new PopupItem("Deck 1"));

        mainButton = new JPanel();
        mainButton.setLayout(new BorderLayout());
        mainButton.setPreferredSize(new Dimension(360, 80));
        mainButton.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,20,20,SharedResource.SIAMESE_DARK,5));
        mainButton.setBorder(new EmptyBorder(0,15,0,15));

        deckName = new JTextField();
        deckName.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        deckName.setOpaque(false);
        deckName.setFont(SharedResource.getCustomSizeFont(32));
        arrowLabel = new JButton(ResourceLoader.loadPicture("assets/Component/DownArrow.png",24,18));
        arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        arrowLabel.setOpaque(false);
        arrowLabel.setContentAreaFilled(false);
        arrowLabel.setFocusPainted(false);
        mainButton.add(deckName, BorderLayout.CENTER);
        mainButton.add(arrowLabel, BorderLayout.EAST);
        arrowLabel.addActionListener(e -> toggleMenu());

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        //menuPanel.setBackground(Color.WHITE);
        //menuPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //menuPanel.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,20,20,false,false,true,true));
        menuPanel.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(330,320));
        scrollPane.setBorder(null);

        menuWrapper = new JPanel();
        menuWrapper.setBounds(20,90,355,330);
        menuWrapper.setBorder(new EmptyBorder(0,10,10,10));
        menuWrapper.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,20,20,true,true,true,true,SharedResource.SIAMESE_DARK,5));
        menuWrapper.add(scrollPane);
        menuWrapper.setVisible(false);

        setUpItem();
        setSelectedIndex(0);
        deckName.setText(items.get(selectedIndex).getFileName());
        updateMenuPanel();

        add(mainButton, BorderLayout.NORTH);

    }

    public void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        getMenuPanel().setVisible(isMenuVisible);
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        revalidate();
        repaint();
    }

    public void updateMenuPanel() {
        menuPanel.removeAll();
        for (int i = 0; i < items.size(); i++) {
            if (i == selectedIndex){
                continue;
            }
            menuPanel.add(items.get(i));
            items.get(i).setNotLast();

        }
        if (menuPanel.getComponents().length>=1){
            ((PopupItem) menuPanel.getComponents()[menuPanel.getComponents().length-1]).setLast();
        }
        revalidate();
        repaint();
    }

    private void setupButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void addItem(PopupItem item) {
        items.add(item);
        updateMenuPanel();
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        deckName.setText(items.get(selectedIndex).getFileName());
            HashMap<Card,Integer> cardList = new HashMap<Card, Integer>();
            try{
                Deck tmp = Deck.LoadDeck(items.get(selectedIndex).getFileName());
                for (Card i : tmp.getCards()){
                    if (cardList.containsKey(i)){
                        int j = cardList.get(i);
                        j+=1;
                        cardList.put(i,j);
                    }
                    else {
                        cardList.put(i,1);
                    }
                }
                DeckCreatorPage.showCardAmount.setCardAmount(tmp.getCards().size());
            }
            catch (FileNotFoundException ex){
                DeckCreatorPage.showCardAmount.setCardAmount(0);
            }
        PopupItem.deckZone.setAllCardLabel(PopupItem.deckZone.createCardLabelSet(cardList));
        PopupItem.deckZone.update();
        updateMenuPanel();
    }
    public void setUpItem(){
        File folder = new File("Assets");
        File[] fileList = folder.listFiles();
        if (fileList.length == 0){
            File newDeck = new File("Assets/Default Deck.deck");
            try(BufferedInputStream sourceDeck = ResourceLoader.loadFileAsStream("assets/ConstantAsset/Default Deck.deck")){
                Files.copy(sourceDeck, newDeck.toPath());
                folder = new File("Assets");
                fileList = folder.listFiles();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        int count = 0;
        items.clear();
        for (File i: fileList){
            items.add(new PopupItem(i.getName().split(".deck")[0],count));
            count += 1;
        }

    }

    public PopupItem getCurrentDeck(){
        return items.get(selectedIndex);
    }

    public String getCurrentName(){
        return deckName.getText();
    }

    public JPanel getMenuPanel() {
        return menuWrapper;
    }
}
