
import java.awt.*;
import javax.swing.*;
//lobby gui
public class Lobby{
    private JFrame frame;
    private JButton leave, ruleSetting, selectDeck, readyUp;
    private JLabel lobby, fourPlayer;
    private JPanel panel1, panel2, panel3, titlePanel;

    public Lobby(){
        //JFrame + Button
        frame = new JFrame("LOBBY");
        leave = new JButton("< Leave Lobby");
        ruleSetting = new JButton("Rule Setting");
        selectDeck = new JButton("Select Deck");
        readyUp = new JButton("Ready");

        //JPanel
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        titlePanel = new JPanel();

       

        //JLabel
        lobby = new JLabel("LOBBY", SwingConstants.CENTER);
        fourPlayer = new JLabel("4 Players", SwingConstants.CENTER);


        lobby.setFont(new Font("Arial", Font.BOLD, 50));
        fourPlayer.setFont(new Font("Arial", Font.BOLD, 35));

        leave.setFont(new Font("Arial", Font.BOLD, 30));
        ruleSetting.setFont(new Font("Arial", Font.BOLD, 30));
        selectDeck.setFont(new Font("Arial", Font.BOLD, 30));
        readyUp.setFont(new Font("Arial", Font.BOLD, 30));
        
        //create profile
        for (int i=0; i<4; i++){
            LobbyProfile profile = new LobbyProfile();
            //lobbyProfile profile = new lobbyProfile("PFPLink", "Name", "Ready");
            JPanel pfpPanel, thePanel;
            JLabel pfp, name, status;

            pfpPanel = new JPanel();
            thePanel = new JPanel();
            
            pfp = new JLabel(profile.getPFP(), SwingConstants.CENTER);
            pfp.setFont(new Font("Arial", Font.BOLD, 40));

            name = new JLabel(profile.getName(), SwingConstants.CENTER);
            name.setFont(new Font("Arial", Font.BOLD, 40));

            status = new JLabel(profile.getStatus(), SwingConstants.CENTER);
            status.setFont(new Font("Arial", Font.BOLD, 40));

            pfpPanel.setLayout(new BorderLayout());
            pfpPanel.add(pfp, BorderLayout.NORTH);
            pfpPanel.add(thePanel, BorderLayout. SOUTH);

            thePanel.setLayout(new GridLayout(2,1));
            thePanel.add(name);
            thePanel.add(status);


            panel2.add(pfpPanel);
        }


        frame.setLayout(new GridLayout(3,1));
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);
        
        //panel 1
        panel1.setLayout(new GridLayout(1,3));
        panel1.add(leave);
        panel1.add(titlePanel);
        panel1.add(ruleSetting);

        //panel 2
        panel2.setLayout(new GridLayout(1,4));
        

        //panel 3
        panel3.setLayout(new GridLayout(1,2));
        panel3.add(selectDeck);
        panel3.add(readyUp);




        titlePanel.setLayout(new GridLayout(2,1));
        titlePanel.add(lobby);
        titlePanel.add(fourPlayer);
        











        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);

    }
    
    public static void main(String[] args) {
        new Lobby();
    }

}

