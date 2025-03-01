package GUI;

import GUI.Page.Page;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lobby2 extends Page {
    private ArrayList<LobbyProfile> allProfile;
    private boolean isHost;
    private int ID;

    private JButton leftLobbyButton;
    private JLabel lobbyText;
    private JLabel playerCountText;
    private JButton ruleSettingButton;
    private JButton selectDeckButton;
    private JButton readyButton;

    private JPanel panel1;
    private JPanel panel1_1;
    private JPanel panel2;
    private JPanel panel3;

    public Lobby2(boolean isHost,ArrayList<LobbyProfile> allProfile){
        this.isHost = isHost;
        this.allProfile = new ArrayList<LobbyProfile>();
        this.allProfile.add(new LobbyProfile("Klong",false,"assets/testLobby/Cat3.jpg"));
        this.allProfile.add(new LobbyProfile("Pupe",false,"assets/testLobby/clown.png"));
        this.ID = 2;

        leftLobbyButton = new JButton("Left Lobby");
        lobbyText = new JLabel("Lobby");
        playerCountText = new JLabel(this.allProfile.size()+" Players");
        ruleSettingButton = new JButton("Rule Setting");
        selectDeckButton = new JButton("Select Deck");
        readyButton = new JButton("Ready");

        lobbyText.setHorizontalAlignment(SwingConstants.CENTER);
        ruleSettingButton.setVerticalAlignment(SwingConstants.EAST);
        playerCountText.setHorizontalAlignment(SwingConstants.CENTER);
        selectDeckButton.setHorizontalAlignment(SwingConstants.CENTER);
        readyButton.setHorizontalAlignment(SwingConstants.CENTER);

        panel1_1 = new JPanel();
        panel1_1.setLayout(new GridLayout(1,3));
        panel1_1.add(new JPanel());
        panel1_1.add(playerCountText);
        panel1_1.add(ruleSettingButton);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(leftLobbyButton,BorderLayout.NORTH);
        panel1.add(lobbyText,BorderLayout.CENTER);
        panel1.add(panel1_1,BorderLayout.SOUTH);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        for (int i = 0;i<this.allProfile.size();i++){
            panel2.add(this.allProfile.get(i));
        }

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,2));
        panel3.add(selectDeckButton);
        panel3.add(readyButton);

        this.mainPanel.setLayout(new BorderLayout());

        this.mainPanel.add(panel1,BorderLayout.NORTH);
        this.mainPanel.add(panel2,BorderLayout.CENTER);
        this.mainPanel.add(panel3,BorderLayout.SOUTH);
    }


}
