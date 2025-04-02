package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class ResourceLoader {
    public static ImageIcon loadPicture(String picture){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        picture = picture.split("\\.")[0]+".webp";
        try{
            String filePath = Paths.get(ResourceLoader.class.getClassLoader().getResource(picture).toURI()).toFile().getAbsolutePath();
            return new ImageIcon(ImageIO.read(new File(filePath)));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon loadPicture(String picture,int width,int height){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        picture = picture.split("\\.")[0]+".webp";
        try{
            String filePath = Paths.get(ResourceLoader.class.getClassLoader().getResource(picture).toURI()).toFile().getAbsolutePath();
            Image loadedImg = (new ImageIcon(ImageIO.read(new File(filePath)))).getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(loadedImg);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage loadBufferedPicture(String picture){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        picture = picture.split("\\.")[0]+".webp";
        try{
            String filePath = Paths.get(ResourceLoader.class.getClassLoader().getResource(picture).toURI()).toFile().getAbsolutePath();
            Image loadedImg = (new ImageIcon(ImageIO.read(new File(filePath)))).getImage();
            BufferedImage bf = new BufferedImage(loadedImg.getWidth(null),loadedImg.getHeight(null),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bf.createGraphics();
            g2d.drawImage(loadedImg,0,0,null);
            g2d.dispose();
            return bf;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage loadBufferedPicture(Image picture){
        //System.out.println(picture+" "+picture.split("\\.")[0]+".webp");
        //picture = picture.split("\\.")[0]+".webp";
            //String filePath = Paths.get(ResourceLoader.class.getClassLoader().getResource(picture).toURI()).toFile().getAbsolutePath();
            Image loadedImg = picture;
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
        //String filePath = Paths.get(ResourceLoader.class.getClassLoader().getResource(picture).toURI()).toFile().getAbsolutePath();
        try (BufferedInputStream f = loadFileAsStream(file)){
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
