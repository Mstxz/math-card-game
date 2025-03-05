package GUI;

import GUI.Component.LobbyProfile;
import GUI.Page.*;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Router {
    private static Page currentPage;
    private static JFrame mainFrame;

    public Router() {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        mainFrame = new JFrame();
        mainFrame.setPreferredSize(new Dimension(1920,1080));
        mainFrame.setMaximumSize(new Dimension(1920,1080));
        Router.setRoute("MainMenu",null);
    }

    public static void main(String[] args) {
        new Router();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void setRoute(String route,Object data){
        Router.currentPage = switch (route){
            case "Demo" -> new DemoPage();
            case "Demo2" -> new Demo2Page();
            case "MainMenu" -> new MainMenuPage();
            case "Avenger" -> new AvengerAssembleGUI(); //game page
            case "Lobby2" -> new Lobby2(false,new ArrayList<LobbyProfile>());
            case "DeckCreator" -> new DeckCreatorPage();
            case "SelMode" -> new SelGameMode();
            default -> null;
        };
        mainFrame.getContentPane().removeAll();
        mainFrame.add(currentPage.getMainPanel());
        mainFrame.setTitle(currentPage.getTitle());
        mainFrame.setVisible(true);
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(JFrame mainFrame) {
        Router.mainFrame = mainFrame;
    }
}
