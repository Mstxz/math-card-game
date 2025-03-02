package GUI.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyProfile extends JPanel implements ActionListener {
    private boolean isHost;
    private String playerName;
    private String picture;
    private boolean isReady;

    private JLabel isHostText;
    private JLabel pictureLabel;
    private JLabel nameText;
    private JLabel isReadyText;
    private JButton readyButton;

    private JPanel infoPanel;

    public LobbyProfile(String playerName, boolean isHost, String picture){
        super();
        this.playerName = playerName;
        this.isHost = isHost;
        this.picture = picture;
        this.isReady = false;

        isHostText = new JLabel("");
        if (isHost){
            isHostText.setText("Host");
        }
        pictureLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource(picture)).getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
        nameText = new JLabel(playerName);
        isReadyText = new JLabel("Not Ready");
        readyButton = new JButton("Ready");

        isHostText.setPreferredSize(new Dimension(isHostText.getWidth(),50));
        isHostText.setHorizontalAlignment(SwingConstants.CENTER);
        nameText.setHorizontalAlignment(SwingConstants.CENTER);
        isReadyText.setHorizontalAlignment(SwingConstants.CENTER);
        readyButton.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));
        infoPanel.add(nameText);
        infoPanel.add(isReadyText);
        infoPanel.add(readyButton);

        readyButton.addActionListener(this);

        this.setLayout(new BorderLayout());

        this.add(isHostText,BorderLayout.NORTH);
        this.add(pictureLabel,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);

        this.setSize(200,200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(readyButton)){
            if (isReady){
                readyButton.setText("Ready");
                isReadyText.setText("Not Ready");
                isReady = false;
            }
            else {
                readyButton.setText("Cancel");
                isReadyText.setText("Ready");
                isReady = true;
            }
        }
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(new LobbyProfile("Klong",false,"assets/testLobby/Cat3.jpg"));
        frame.add(new LobbyProfile("Pupe",false,"assets/testLobby/clown.png"));
        frame.add(new LobbyProfile("Pleng's Cat",false,"assets/testLobby/pleng_cat.png"));
        frame.add(new LobbyProfile("Bob Hair Karn",true,"assets/testLobby/pupe_karn_1.png"));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
