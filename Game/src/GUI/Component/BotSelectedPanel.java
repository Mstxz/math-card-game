package GUI.Component;

import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotSelectedPanel extends JPanel implements ActionListener {
    private JLabel namePanel;
    private JLabel botPicture;

    private JButton leftArrow;
    private JButton rightArrow;

    private JPanel descriptionPanel;
    private JTextArea descriptionLabel;

    private ArrayList<BotInfo> botList;

    private int currentIndex = 0;

    public BotSelectedPanel(){
        loadBot();
        namePanel = new JLabel(botList.get(0).getName());
        botPicture = new JLabel(botList.get(0).getImg());
        leftArrow = new JButton(ResourceLoader.loadPicture("assets/Component/DownArrow.webp"));
        rightArrow = new JButton(ResourceLoader.loadPicture("assets/Component/DownArrow.webp"));

        namePanel.setFont(SharedResource.getCustomSizeFont(30));

        descriptionLabel = new JTextArea(botList.get(0).getDescription());
        descriptionLabel.setEditable(false);

        descriptionPanel = new JPanel();
        descriptionPanel.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(namePanel);
        p1.add(p4,BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(leftArrow,BorderLayout.WEST);
        p2.add(rightArrow,BorderLayout.EAST);
        p2.add(botPicture,BorderLayout.CENTER);

        p1.add(p2,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(p1,BorderLayout.NORTH);

        descriptionPanel.add(descriptionLabel);

        this.add(descriptionPanel,BorderLayout.CENTER);

        leftArrow.addActionListener(this);
        rightArrow.addActionListener(this);
    }

    public void loadBot(){
        botList = new ArrayList<BotInfo>();
        botList.add(new BotInfo(null,"Karn Bob","Karn Bob is a master level player of Purrfect equation.\n" +
                "(S)he will use various strategy to win.","assets/Profile/Karn Bob.webp"));
        botList.add(new BotInfo(null,"Pupr","How can I play this game?","assets/Profile/Pupr.webp"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(leftArrow)){
            currentIndex-=1;
            if (currentIndex<0){
                currentIndex = botList.size()-1;
            }
        }
        else if (e.getSource().equals(rightArrow)){
            currentIndex+=1;
            if (currentIndex>=botList.size()){
                currentIndex = 0;
            }
        }
        update();
    }

    public void update(){
        namePanel.setText(botList.get(currentIndex).getName());
        botPicture.setIcon(botList.get(currentIndex).getImg());
        descriptionLabel.setText(botList.get(currentIndex).getDescription());
    }

    public static void main(String[] args) {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        JFrame frame = new JFrame();
        BotSelectedPanel filterZone = new BotSelectedPanel();
        frame.add(filterZone,BorderLayout.CENTER);

        frame.setSize(500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
