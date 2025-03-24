package GUI.Setting.Component;

import utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ProfilePicture implements Serializable {
    private String profileName;
    private String description;
    private ImageIcon image;
    private JButton button;

    public ProfilePicture(String profileName,String description){
        this.profileName = profileName;
        this.description = description;
        this.image = ResourceLoader.loadPicture(getProfileURL(),150,150);
        button = new JButton(this.image){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.setIcon(ResourceLoader.loadPicture(getProfileURL(),getWidth(),getHeight()));
            }
        };
        button.setName(profileName);
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public String getProfileURL(){
        return "assets/Profile/"+profileName+".png";
    }
}
