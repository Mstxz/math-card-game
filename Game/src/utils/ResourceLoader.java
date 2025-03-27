package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ResourceLoader {
    public static ImageIcon loadPicture(String picture){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        //picture = picture.split("\\.")[0]+".webp";
        return new ImageIcon(Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource(picture)));
    }

    public static ImageIcon loadPicture(String picture,int width,int height){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        //picture = picture.split("\\.")[0]+".webp";
        URL imgURL = Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource(picture));
        Image loadedImg = (new ImageIcon(imgURL)).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        return new ImageIcon(loadedImg);
    }

    public static BufferedImage loadBufferedPicture(String picture){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        //picture = picture.split("\\.")[0]+".webp";
        URL imgURL = Objects.requireNonNull(ResourceLoader.class.getClassLoader().getResource(picture));
        Image loadedImg = new ImageIcon(imgURL).getImage();
        System.out.println(picture+" "+loadedImg.getWidth(null) + " "+loadedImg.getHeight(null));
        BufferedImage bf = new BufferedImage(loadedImg.getWidth(null),loadedImg.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bf.createGraphics();
        g2d.drawImage(loadedImg,0,0,null);
        g2d.dispose();
        return bf;
    }

    public static BufferedInputStream loadFileAsStream(String file){
        return (BufferedInputStream) ResourceLoader.class.getClassLoader().getResourceAsStream(file);
    }

    public static ArrayList<String> readFile(String file){
        try {
            BufferedInputStream f = loadFileAsStream(file);
            ArrayList<String> stringArrayList = new ArrayList<String>();
            int current = f.read();
            String currentS = "";
            while (current!=-1){
                while ((char) (current) != '\n'&&current!=-1&&current!=13){
                    //System.out.print((char) (current));
                    currentS+=(char)(current);
                    current = f.read();
                }
                if (current==13){
                    f.read();
                    //f.read();
                }
                //System.out.print(currentS);
                //System.out.println(currentS);
                stringArrayList.add(currentS);
                currentS = "";
                current = f.read();
            }
            return stringArrayList;
        }
        catch (IOException e){
            return new ArrayList<String>();
        }
    }
}
