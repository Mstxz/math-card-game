package GUI.Component;

import GUI.Page.DeckCreatorPage;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class FilterZone extends JPanel {
    private static FilterButton baby;
    private static FilterButton easy;
    private static FilterButton medium;
    private static FilterButton hard;
    private static FilterButton expert;

    private static FilterButton black;
    private static FilterButton white;
    private static FilterButton orange;
    private static FilterButton caligo;

    private JPanel zone1;
    private JPanel zone2;

    private static FilterButton zone1Selected = null;
    private static FilterButton zone2Selected = null;

    public FilterZone(){
        zone1 = new JPanel();
        zone1.setLayout(new FlowLayout());

        baby = new FilterButton("baby","assets/Component/Cat_lvl0_.png");
        easy = new FilterButton("easy","assets/Component/Cat_lvl1_.png");
        medium = new FilterButton("medium","assets/Component/Cat_lvl2_.png");
        hard = new FilterButton("hard","assets/Component/Cat_lvl3_.png");
        expert = new FilterButton("expert","assets/Component/Cat_lvl4_.png");

        zone1.add(baby);
        zone1.add(easy);
        zone1.add(medium);
        zone1.add(hard);
        zone1.add(expert);

        zone2 = new JPanel();
        zone2.setLayout(new FlowLayout());

        black = new FilterButton("Red","assets/Component/Black.webp");
        caligo = new FilterButton("Yellow","assets/Component/Caligo.webp");
        orange = new FilterButton("Green","assets/Component/Orange.webp");
        white = new FilterButton("Blue","assets/Component/White.webp");

        zone2.add(white);
        zone2.add(black);
        zone2.add(orange);
        zone2.add(caligo);

        zone1.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));
        zone2.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));

        //zone1.setPreferredSize(new Dimension(500,500));
        this.add(zone1);
        this.add(zone2);
    }

    public static void update(FilterButton e){
        switch (e.getText()){
            case "easy":
            case "baby":
            case "medium":
            case "hard":
                case "expert":
                    if (e.equals(zone1Selected)){
                        zone1Selected.update(false);
                        zone1Selected = null;
                        setCardButton();
                        return;
                    }
                if (zone1Selected != null){
                    zone1Selected.update(false);
                }
                zone1Selected = e;
                zone1Selected.update(true);
                break;
            case "Red":
            case "Blue":
            case "Green":
                case "Yellow":
                    if (e.equals(zone2Selected)){
                        zone2Selected.update(false);
                        zone2Selected = null;
                        setCardButton();
                        return;
                    }
                if (zone2Selected != null){
                    zone2Selected.update(false);
                }
                zone2Selected = e;
                zone2Selected.update(true);
                break;
        }
        setCardButton();
    }

    private static void setCardButton(){
        HashSet<CardButton> tmp = new HashSet<CardButton>();
        if (zone1Selected == null && zone2Selected == null){
            tmp.addAll(easy.getCardButtons());
            tmp.addAll(medium.getCardButtons());
            tmp.addAll(hard.getCardButtons());
            tmp.addAll(expert.getCardButtons());
            tmp.addAll(baby.getCardButtons());
        }
        else if (zone1Selected == null){
            tmp.addAll(zone2Selected.getCardButtons());
        }
        else if (zone2Selected == null){
            tmp.addAll(zone1Selected.getCardButtons());
        }
        else {
            tmp.addAll(zone1Selected.getCardButtons());
            tmp.retainAll(zone2Selected.getCardButtons());
        }
        DeckCreatorPage.update(tmp);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        FilterZone filterZone = new FilterZone();
        frame.add(filterZone,BorderLayout.CENTER);

        frame.setSize(500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public FilterButton getBaby() {
        return baby;
    }

    public void setBaby(FilterButton baby) {
        this.baby = baby;
    }

    public FilterButton getEasy() {
        return easy;
    }

    public void setEasy(FilterButton easy) {
        this.easy = easy;
    }

    public FilterButton getMedium() {
        return medium;
    }

    public void setMedium(FilterButton medium) {
        this.medium = medium;
    }

    public FilterButton getHard() {
        return hard;
    }

    public void setHard(FilterButton hard) {
        this.hard = hard;
    }

    public FilterButton getExpert() {
        return expert;
    }

    public void setExpert(FilterButton expert) {
        this.expert = expert;
    }

    public FilterButton getBlack() {
        return black;
    }

    public void setBlack(FilterButton black) {
        this.black = black;
    }

    public FilterButton getWhite() {
        return white;
    }

    public void setWhite(FilterButton white) {
        this.white = white;
    }

    public FilterButton getOrange() {
        return orange;
    }

    public void setOrange(FilterButton orange) {
        this.orange = orange;
    }

    public FilterButton getCaligo() {
        return caligo;
    }

    public void setCaligo(FilterButton caligo) {
        this.caligo = caligo;
    }
}
