package GUI;

import GUI.Component.LobbyProfile;
import GUI.Page.*;

import javax.swing.*;
import java.util.ArrayList;

public class Router {
    private static Page currentPage;
    private static JFrame mainFrame;

    public Router() {
        mainFrame = new JFrame();
        Router.setRoute("DeckCreator",null);
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
            case "Avenger" -> new AvengerAssembleGUI();
            case "Lobby2" -> new Lobby2(false,new ArrayList<LobbyProfile>());
            case "DeckCreator" -> new DeckCreatorPage();
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
