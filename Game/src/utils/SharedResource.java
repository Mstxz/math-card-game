package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SharedResource {
    private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final Color SIAMESE_BRIGHT = new Color(221, 218, 210);
    public static final Color SIAMESE_LIGHT = new Color(191, 180, 168);
    public static final Color SIAMESE_BASE = new Color(100, 90, 98);
    public static final Color SIAMESE_DARK = new Color(72, 62, 56);

    public static void loadFont(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,ResourceLoader.loadFileAsStream("assets/Font/MadimiOne-Regular.ttf"));
            //System.out.println(font.getFontName());
            ge.registerFont(font);
        }
        catch (FontFormatException | IOException exception){
            return;
        }

    }

    public static Font getFont48(){
        return new Font("Madimi One Regular",Font.PLAIN,48);
    }

    public static Font getCustomSizeFont(int size){
        return new Font("Madimi One Regular",Font.PLAIN,size);
    }

    public static void setAllFont(){
        UIDefaults u = UIManager.getDefaults();
        u.put("Button.font",new Font("Madimi One Regular",Font.PLAIN,24));
        u.put("Label.font",new Font("Madimi One Regular",Font.PLAIN,24));
    }
}
