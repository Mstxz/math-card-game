package GUI.Setting.Component;

import AudioPlayer.SFXPlayer;
import GUI.Setting.Controller.SettingController;
import GUI.Setting.Controller.UserProfile;
import GUI.Setting.UserPreference;
import utils.UIManager.ButtonUI;
import utils.UIManager.CustomScrollBarUI;
import utils.UIManager.RoundPanelUI;
import utils.SharedResource;
import utils.UIManager.TextFieldUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Profile extends JPanel implements ActionListener {
    private JPanel panel1; // profile zone
    private JPanel panel2; // preview profile zone
    private JPanel panel3; //

    private JPanel panel4;

    private JTextField name;
    private JLabel profileImage;
    private JButton renameButton;
    private JButton saveButton;

    private JLabel selectedProfile;
    private JLabel profileNameLabel;
    private JLabel descriptionLabel;

    private ProfilePicture selectedImage;
    private String selectedKey;

    public Profile(){
        selectedImage = UserPreference.getInstance().getProfile().getProfilePicture();
        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0,100));

        panel1 = new JPanel();
        //panel1.setPreferredSize(new Dimension(500,300));
        panel1.setLayout(new BorderLayout(20,10));
        JPanel panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(500,50));
        panel5.setLayout(new BorderLayout(10,10));

        name = new JTextField(UserPreference.getInstance().getProfile().getName());
        name.setEditable(false);
        name.setFocusable(false);
        name.setFont(SharedResource.getCustomSizeFont(30));
        name.setOpaque(false);
        name.setMargin(new Insets(5,5,5,5));


        profileImage = new JLabel(selectedImage.getImage());
        selectedKey = UserPreference.getInstance().getProfile().getProfileName();
        renameButton = new JButton("Rename");
        renameButton.setUI(new ButtonUI());
        renameButton.setPreferredSize(new Dimension(100,70));
        saveButton = new JButton("Save");
        saveButton.setUI(new ButtonUI());
        saveButton.setPreferredSize(new Dimension(100,70));
        panel1.add(profileImage,BorderLayout.WEST);
        panel5.add(name,BorderLayout.NORTH);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayout(1,2,10,0));
        panel6.add(renameButton);
        panel6.add(saveButton);
        panel6.setBackground(SharedResource.SIAMESE_LIGHT);

        panel5.add(panel6,BorderLayout.SOUTH);
        panel5.setPreferredSize(new Dimension(300,50));
        panel5.setOpaque(false);
        panel1.setOpaque(false);
        panel1.add(panel5,BorderLayout.CENTER);

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(20,10));

        //panel2.setPreferredSize(new Dimension(500,100));

        JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout());
        panel7.setPreferredSize(new Dimension(300,50));

        selectedProfile = new JLabel(selectedImage.getImage());
        profileNameLabel = new JLabel(selectedImage.getProfileName());
        descriptionLabel = new JLabel();
        descriptionLabel.setText("<html><div style='text-align: left; display: flex; justify-content: center;'>"+selectedImage.getDescription()+"</div></html>");
        profileNameLabel.setFont(SharedResource.getCustomSizeFont(28));
        profileNameLabel.setForeground(SharedResource.SIAMESE_BRIGHT);
        descriptionLabel.setForeground(SharedResource.SIAMESE_BRIGHT);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setFont(SharedResource.getCustomSizeFont(20));

        panel2.add(selectedProfile,BorderLayout.WEST);
        panel2.setOpaque(false);

        panel7.add(profileNameLabel,BorderLayout.NORTH);
        panel7.add(descriptionLabel,BorderLayout.CENTER);
        panel7.setOpaque(false);

        panel2.add(panel7,BorderLayout.CENTER);
        JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2.setAlignmentY(0.5f);
        panel8.add(panel2);
        panel8.setBorder(new EmptyBorder(20,20,40,40));

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        panel3.setBorder(new EmptyBorder(10,10,10,10));
        panel3.setPreferredSize(new Dimension(300,(Math.ceilDiv(UserProfile.profilePictureList.size(),2) * 180 + 40)));
        panel3.setBackground(SharedResource.SIAMESE_BRIGHT);
        for (String s : UserProfile.profilePictureList.keySet()) {
            ProfilePicture o = UserProfile.profilePictureList.get(s);
            o.getButton().setPreferredSize(new Dimension(150,150));
            panel3.add(o.getButton());
            o.getButton().addActionListener(this);
        }
        selectedImage.setSelect(true);



        JScrollPane scrollPane = new JScrollPane(panel3);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,40,40));
        profilePanel.setBorder(new EmptyBorder(10,10,10,10));
        profilePanel.add(scrollPane);


        panel8.setUI(new RoundPanelUI(SharedResource.SIAMESE_DARK,30,30));
        panel4.setBackground(SharedResource.SIAMESE_LIGHT);
        panel4.add(panel1,BorderLayout.NORTH);
        panel4.add(panel8,BorderLayout.CENTER);
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT,30,30,false,true,true,true));
        this.setBorder(new EmptyBorder(20,20,40,40));
        this.setLayout(new BorderLayout(50,10));
        this.add(panel4,BorderLayout.WEST);
        this.add(profilePanel,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(1100,500));

        saveButton.addActionListener(this);
        renameButton.addActionListener(this);

        selectedImage.setSelect(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)){
            UserPreference.getInstance().setProfile(new UserProfile(name.getText(),selectedKey) );
            SettingController.updatePreference();
            System.out.println("Save");
            profileImage.setIcon(UserPreference.getInstance().getProfile().getProfilePicture().getImage());
            renameButton.setText("Rename");
            name.setEditable(false);
            name.setFocusable(false);
            name.setOpaque(false);
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Deck_Confirm.wav");
        }
        else if (e.getSource().equals(renameButton)){
            if (((JButton)(e.getSource())).getText().equals("Rename")){
                renameButton.setText("Cancel");
                name.setOpaque(true);
                name.setFocusable(true);
                name.setEditable(true);
                name.requestFocus();
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Deck_Action.wav");
            }
            else if (((JButton)(e.getSource())).getText().equals("Cancel")){
                name.setEditable(false);
                name.setText(UserPreference.getInstance().getProfile().getName());
                renameButton.setText("Rename");
                name.setFocusable(false);
                name.setOpaque(false);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Deck_cancel.wav");
            }
        }
        else if (e.getSource() instanceof JButton){
            selectedImage.setSelect(false);
            ProfilePicture o =UserProfile.profilePictureList.get(((JButton) e.getSource()).getName());
            selectedKey = ((JButton) e.getSource()).getName();
            selectedImage = o;
            selectedImage.setSelect(true);
            selectedProfile.setIcon(selectedImage.getImage());
            profileNameLabel.setText(selectedImage.getProfileName());
            descriptionLabel.setText("<html><div style='text-align: left; display: flex; justify-content: center;'>"+selectedImage.getDescription()+"</div></html>");
            panel3.revalidate();
            panel3.repaint();
            }
        }

//    @Override
//    public void paint(Graphics g) {
//        //super.revalidate();
//        super.paint(g);
//        panel3.revalidate();
//        panel3.repaint();
//    }
}

