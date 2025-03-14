package GUI;

import static GUI.Router.*;

public class SettingController {
    public static void updateResolution(String resolution){
        if (resolution.equals("1920x1080")){
            Router.getMainFrame().setSize(1920,1080);
        }
        else if (resolution.equals("1366x768")){
            Router.getMainFrame().setSize(1366,768);
        }
    }
}
