
package GUI.Page;

import AudioPlayer.BGMPlayer;
import AudioPlayer.SFXPlayer;
import GUI.Component.*;
import GUI.Router;
import GameSocket.NIOClient;
import GameSocket.NIOServer;
import utils.SharedResource;
import utils.UIManager.ButtonUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class RoomSelect extends Page implements ActionListener, KeyListener {
    private JPanel optionPanel;
    private JPanel middlePanel;
    private JLabel header;
    private JLabel createRoomLabel;
    private JLabel joinRoomLabel;
    private JButton  createButton;
    private JButton  joinButton;
    private JTextField hostIpField;
    private ExitButton exitLabel;
    private JPanel topPanel;
    private boolean loading;

    public RoomSelect() {
        topPanel = new JPanel();
        middlePanel = new JPanel();
        optionPanel = new JPanel();
        header = new JLabel("Select");
        header.setFont(SharedResource.getCustomSizeFont(48));
        header.setHorizontalAlignment(SwingConstants.CENTER);

        createRoomLabel = new JLabel("Create Room");
        createRoomLabel.setBackground(SharedResource.SIAMESE_BRIGHT);
        createRoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinRoomLabel = new JLabel("Join Room");
        joinRoomLabel.setBackground(SharedResource.SIAMESE_BRIGHT);
        joinRoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        createButton = new JButton("Create");
        createButton.setUI(new ButtonUI());
        createButton.setPreferredSize(new Dimension(350,70));
        createButton.setMaximumSize(new Dimension(350,70));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        joinButton = new JButton ("Join");
        joinButton.setUI(new ButtonUI());
        joinButton.setPreferredSize(new Dimension(350,70));
        joinButton.setMaximumSize(new Dimension(350,70));
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        hostIpField = new JTextField("Host IP");
        hostIpField.setFont(SharedResource.getCustomSizeFont(28));
        hostIpField.setHorizontalAlignment(SwingConstants.CENTER);
        hostIpField.setBounds(0, 0, 500, 90);
        hostIpField.setColumns(50);
        hostIpField.setMaximumSize(new Dimension(580,90));
        hostIpField.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostIpField.setForeground(new Color(172,158,145));
        //hostIpField.setBorder(BorderFactory.createLineBorder(new Color(100,90,82),3));
        hostIpField.setBorder(new RoundBorder(new Color(100, 90, 82), null, 3, 20));
        hostIpField.setOpaque(false); // ทำให้พื้นหลังของ JTextField โปร่งใส
        hostIpField.setBackground(new Color(221, 218, 210));

// เพิ่ม FocusListener เพื่อให้ placeholder หายไปเมื่อกดช่องกรอก และกลับมาเมื่อเว้นว่าง
        hostIpField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (hostIpField.getText().equals("Host IP")) {
                    hostIpField.setText("");
                    hostIpField.setForeground(SharedResource.SIAMESE_DARK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hostIpField.getText().isEmpty()) {
                    hostIpField.setText("Host IP");
                    hostIpField.setForeground(SharedResource.SIAMESE_BASE); // กลับมาเป็น placeholder สีเทา
                }
            }
        });

        exitLabel = new ExitButton("SelMode"){
            @Override
            public void cleanUp() {
                mainFrame.removeKeyListener(RoomSelect.this);
            }
        };
        mainFrame.addKeyListener(this);

        exitLabel.setVerticalAlignment(SwingConstants.NORTH);
        exitLabel.setHorizontalAlignment(SwingConstants.LEFT);

        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));
        optionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionPanel.setPreferredSize(new Dimension(300,600));
        optionPanel.add(createRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(createButton);
        optionPanel.add(Box.createRigidArea(new Dimension(0,50)));
        optionPanel.add(joinRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(hostIpField);
        optionPanel.add(Box.createRigidArea(new Dimension(0,30)));
        optionPanel.add(joinButton);
        optionPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(exitLabel,BorderLayout.WEST);
        topPanel.add(header);
        JLabel emptySpace = new JLabel();
        emptySpace.setPreferredSize(new Dimension(100,40));
        topPanel.add(emptySpace,BorderLayout.EAST);
        topPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        middlePanel.setLayout(new BorderLayout(0,100));
        middlePanel.add(optionPanel);
        middlePanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        mainPanel.setLayout(new BorderLayout(0,100));
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(optionPanel,BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(20, 40, 80, 40));

        createButton.addActionListener(this);
        createButton.setFocusable(false);
        joinButton.addActionListener(this);
        joinButton.setFocusable(false);

        setupMainPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createButton)){
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Start.wav");
            BGMPlayer.stopBackgroundMusic();
            NIOServer.getInstance().stopServer();
            NIOServer.getInstance().start();
            NIOClient client = new NIOClient();
            Loader l = new Loader(this,"Creating Session"){
                @Override
                public void running(){
                    try{
                        if (NIOServer.getInstance().isBound()){
                            client.connect("localhost");
                            while (!client.isLobbyLoaded()){
                                Thread.sleep(1000);
                            }
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public boolean closeCondition(){
                    try{
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return client.isLobbyLoaded();
                }
                @Override
                public void onClose(){
                    super.onClose();
                    mainFrame.removeKeyListener(RoomSelect.this);
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
        else if(e.getSource().equals(joinButton)){
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Game_Start.wav");
            NIOClient client = new NIOClient();
            client.connect(hostIpField.getText());
            Loader l = new Loader(this,"Joining Session"){
                @Override
                public void running(){
                    try{
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public boolean closeCondition(){
                    return client.isLobbyLoaded();
                }
                @Override
                public void onClose(){
                    super.onClose();
                    mainFrame.removeKeyListener(RoomSelect.this);
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code;

        if (!this.getMainPanel().isFocusable())
            return;
        code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE){
            this.getMainFrame().removeKeyListener(this);
            Router.setRoute("SelMode",null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        new RoomSelect();
    }
}