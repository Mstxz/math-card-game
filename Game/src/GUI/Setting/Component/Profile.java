package GUI.Setting.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

public class Profile extends JPanel implements ActionListener {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private JPanel panel4;

    private JTextField name;
    private JLabel profileImage;
    private JButton renameButton;
    private JButton saveButton;

    private JLabel selectedProfile;
    private JLabel profileNameLabel;
    private JLabel descriptionLabel;

    private ProfilePicture selectedImage;

    private HashMap<String,ProfilePicture> profilePictureList = new HashMap<String,ProfilePicture>();
    public Profile(){
        loadProfileList();
        selectedImage = profilePictureList.get("Klong ha");
        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());

        name = new JTextField("Klong Ha");
        profileImage = new JLabel(selectedImage.getImage());
        renameButton = new JButton("Rename");
        saveButton = new JButton("Save");

        panel1.add(profileImage,BorderLayout.WEST);
        panel5.add(name,BorderLayout.NORTH);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayout(1,2));
        panel6.add(renameButton);
        panel6.add(saveButton);

        panel5.add(panel6,BorderLayout.SOUTH);
        panel1.add(panel5,BorderLayout.CENTER);

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout());

        selectedProfile = new JLabel(selectedImage.getImage());
        profileNameLabel = new JLabel(selectedImage.getProfileName());
        descriptionLabel = new JLabel(selectedImage.getDescription());

        panel2.add(selectedProfile,BorderLayout.WEST);

        panel7.add(profileNameLabel,BorderLayout.NORTH);
        panel7.add(descriptionLabel,BorderLayout.CENTER);

        panel2.add(panel7,BorderLayout.CENTER);

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2,2));
        Iterator i = profilePictureList.keySet().iterator();
        while (i.hasNext()){
            ProfilePicture o = profilePictureList.get((String) i.next());
            panel3.add(o.getButton());
            o.getButton().addActionListener(this);
            System.out.println(o.getProfileName());
        }

        panel4.add(panel1,BorderLayout.NORTH);
        panel4.add(panel2,BorderLayout.CENTER);

        this.add(panel4,BorderLayout.WEST);
        this.add(panel3,BorderLayout.CENTER);
    }

    public void loadProfileList(){
        profilePictureList.put("Klong ha",new ProfilePicture("Klong ha","Klong Eng Ha","assets/testLobby/Cat3.jpg"));
        profilePictureList.put("Clown",new ProfilePicture("Clown","Heavy is teammate","assets/testLobby/clown.png"));
        profilePictureList.put("Pleng's cat",new ProfilePicture("Pleng's cat","I don't know, this is not my cat!","assets/testLobby/pleng_cat.png"));
        profilePictureList.put("Karn Bob",new ProfilePicture("Karn Bob","Is that him cutting a new hair?","assets/testLobby/pupe_karn_1.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            Iterator i = profilePictureList.keySet().iterator();
            while (i.hasNext()){
                ProfilePicture o = profilePictureList.get((String) i.next());
                if (e.getSource().equals(o.getButton())){
                    selectedImage = o;
                    selectedProfile.setIcon(selectedImage.getImage());
                    profileNameLabel.setText(selectedImage.getProfileName());
                    descriptionLabel.setText(selectedImage.getDescription());

                }
            }
        }
    }
}
