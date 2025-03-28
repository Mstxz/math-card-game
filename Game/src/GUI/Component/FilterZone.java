package GUI.Component;

import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import java.awt.*;

public class FilterZone extends JPanel {
    private FilterButton baby;
    private FilterButton easy;
    private FilterButton medium;
    private FilterButton hard;
    private FilterButton expert;

    private FilterButton black;
    private FilterButton white;
    private FilterButton orange;
    private FilterButton caligo;

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
                if (zone2Selected != null){
                    zone2Selected.update(false);
                }
                zone2Selected = e;
                zone2Selected.update(true);
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        FilterZone filterZone = new FilterZone();
        frame.add(filterZone,BorderLayout.CENTER);

        frame.setSize(500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
