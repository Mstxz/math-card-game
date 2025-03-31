package GUI.Component;

import GUI.Page.Page;

import java.util.ArrayList;

public class HowToPlaySlide extends SlideShow{
    public static ArrayList<String> filePath;
    static {
        filePath = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            filePath.add("assets/SlideShow/HowToPlay-"+i + ".webp");
        }
    }
    public HowToPlaySlide(Page page){
        super(page,HowToPlaySlide.filePath);
    }
}
