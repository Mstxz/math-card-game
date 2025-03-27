
package GUI.Page;

import GUI.Component.*;
import GUI.Router;
import GameSocket.NIOClient;
import GameSocket.NIOServer;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class RoomSelect extends Page implements ActionListener {
    private JPanel optionPanel;
    private JPanel middlePanel;
    private JLabel header;
    private JLabel createRoomLabel;
    private JLabel joinRoomLabel;
    private PlayerVSPlayerSelectButton  createButton;
    private PlayerVSPlayerSelectButton  joinButton;
    private JTextField hostIpField;
    private ExitButton exitLabel;
    private boolean loading;

    public RoomSelect() {
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

        createButton = new PlayerVSPlayerSelectButton("Create");
        createButton.setPreferredSize(new Dimension(350, 80));

        JLabel createButtonLabel = new JLabel("                     Create                     ", SwingConstants.CENTER);
        createButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        createButtonLabel.setForeground(new Color(102, 142, 169));
        createButtonLabel.setBounds(0, 0, 356, 99);
        createButton.add(createButtonLabel);
//        createButton.setBorder(new EmptyBorder(10,60,10,60));
//        createButton.setPreferredSize(new Dimension(200,60));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinButton = new PlayerVSPlayerSelectButton ("Join");
        joinButton.setPreferredSize(new Dimension(350, 80));

        JLabel joinButtonLabel = new JLabel("                        Join                        ", SwingConstants.CENTER);
        joinButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        joinButtonLabel.setFont(SharedResource.getCustomSizeFont(28));
        joinButtonLabel.setForeground(new Color(102, 142, 169));
        joinButtonLabel.setBounds(0, 0, 356, 99);
        joinButton.add(joinButtonLabel);
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

//        joinButton.setBorder(new EmptyBorder(10,60,10,60));
//        joinButton.setPreferredSize(new Dimension(200,60));
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        hostIpField = new JTextField("Room ID");
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
                if (hostIpField.getText().equals("Room ID")) {
                    hostIpField.setText("");
                    hostIpField.setForeground(SharedResource.SIAMESE_DARK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hostIpField.getText().isEmpty()) {
                    hostIpField.setText("Room ID");
                    hostIpField.setForeground(SharedResource.SIAMESE_BASE); // กลับมาเป็น placeholder สีเทา
                }
            }
        });

        exitLabel = new ExitButton("SelMode");
        exitLabel.setVerticalAlignment(SwingConstants.NORTH);
        exitLabel.setHorizontalAlignment(SwingConstants.LEFT);

        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));
        optionPanel.setPreferredSize(new Dimension(300,600));
        optionPanel.add(createRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(createButton);
        optionPanel.add(Box.createRigidArea(new Dimension(0,20)));
        optionPanel.add(joinRoomLabel);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(hostIpField);
        optionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        optionPanel.add(joinButton);
        optionPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        middlePanel.setLayout(new BorderLayout(0,100));
        middlePanel.add(header,BorderLayout.NORTH);
        middlePanel.add(optionPanel);
        middlePanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        mainPanel.setLayout(new GridLayout(1,3));
        mainPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        mainPanel.add(exitLabel);
        mainPanel.add(middlePanel);
        mainPanel.add(new JLabel());

        createButton.addActionListener(this);
        joinButton.addActionListener(this);

        setupMainPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(createButton)){
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
                    return client.isLobbyLoaded();
                }
                @Override
                public void onClose(){
                    super.onClose();
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
        else if(e.getSource().equals(joinButton)){
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
                    Router.setRoute("Lobby",client);
                }
            };
            l.startLoad();
        }
    }

    public static void main(String[] args) {
        new RoomSelect();
    }
}