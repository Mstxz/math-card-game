package GUI.Component;

import GUI.Page.OverlayPlacement;
import GUI.Page.Page;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;

public class CreateSessionLoader extends Loader {
    private String text;
    private JLabel loadingText;
    private JLabel loaderIcon;
    public CreateSessionLoader(Page parentGUI){
        super(parentGUI);
        this.text = "Create Session";
        loadingText.setText(text);
    }



    public void startLoad(){
        while (!closeCondition()){

        }
        onClose();
    }
    @Override
    public boolean closeCondition(){
        return true;
    }
    public void onClose(){

    }
}
