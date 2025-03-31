package GUI.Setting.Component;

import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Serializable;

public class ProfilePicture implements Serializable {
    private String profileName;
    private String description;
    private ImageIcon image;
    private JButton button;
    private boolean isSelect = false;

    public ProfilePicture(String profileName,String description){
        this.profileName = profileName;
        this.description = description;
        this.image = ResourceLoader.loadPicture(getProfileURL(),150,150);
        button = new JButton(this.image){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isSelect){
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(SharedResource.SKYBLUE_DARK);
                    g2.setStroke(new BasicStroke(10));
                    g.drawRect(0,0,getWidth(),getHeight());
                }
                //this.setIcon(ResourceLoader.loadPicture(getProfileURL(),getWidth(),getHeight()));
            }
        };
        button.setFocusPainted(false);
        button.setBorderPainted(false);
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
        return "assets/Profile/"+profileName+".webp";
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
        button.repaint();
    }
}
