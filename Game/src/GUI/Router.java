package GUI;

import GUI.Component.LobbyProfile;
import GUI.Page.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

import utils.SharedResource;

public class Router {
    private static Page currentPage;
    private static JFrame mainFrame;
    public Router() {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        mainFrame = new JFrame();
        mainFrame.setSize(1920,1080);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        Router.setRoute("MainMenu",null);
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Router();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void setRoute(String route,Object data){
        if (currentPage != null){
            mainFrame.remove(currentPage.getLayeredPane());
        }
        Router.currentPage = switch (route){
            case "Demo" -> new DemoPage();
            case "Demo2" -> new Demo2Page();
            case "MainMenu" -> new MainMenuPage();
            case "Avenger" -> new AvengerAssembleGUI(); //game page
            case "Lobby2" -> new Lobby2(false,new ArrayList<LobbyProfile>());
            case "DeckCreator" -> new DeckCreatorPage();
            case "SelMode" -> new SelGameMode();
            case "Player" -> new PlayerVsPlayer();
            default -> null;
        };
        currentPage.setUpLayeredPane();
        mainFrame.add(currentPage.getLayeredPane());

        mainFrame.setTitle(currentPage.getTitle());
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void refresh(){
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(JFrame mainFrame) {
        Router.mainFrame = mainFrame;
    }

}
