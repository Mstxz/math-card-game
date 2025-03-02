package utils;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Objects;

public class ResourceLoader {
    public static ImageIcon loadPicture(String picture){
        return new ImageIcon(Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource("assets/" + picture)));
    }

    public static ImageIcon loadPicture(String picture,int width,int height){
        URL imgURL = Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource("assets/" + picture));
        Image loadedImg = (new ImageIcon(imgURL)).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(loadedImg);
    }

    public static BufferedInputStream loadFileAsStream(String file){
        return (BufferedInputStream) ResourceLoader.class.getClassLoader().getResourceAsStream(file);
    }
}
