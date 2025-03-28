package GUI;

import GUI.Component.Game;
import GUI.Component.LobbyProfile;
import GUI.Page.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import GUI.Setting.Controller.SettingController;
import GameSocket.NIOClient;
import utils.SharedResource;

public class Router implements ComponentListener {
    private static Page currentPage;
    private static JFrame mainFrame;
    private static JLayeredPane layeredPane;
    public Router() {
        SharedResource.loadFont();
        SharedResource.setAllFont();
        ImageIO.scanForPlugins();
        mainFrame = new JFrame();
        mainFrame.setSize(1920,1080);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addComponentListener(this);
        mainFrame.setVisible(true);
        layeredPane = new JLayeredPane();
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }
        mainFrame.setContentPane(layeredPane);
        SettingController.update();
    }

    public static void main(String[] args) {
        new Router();
        Router.setRoute("MainMenu",null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void setRoute(String route,Object data){
        if (currentPage != null){
            layeredPane.remove(currentPage.getMainPanel());
            layeredPane.remove(currentPage.getOverlayPanel());
            //mainFrame.remove(currentPage.getMainPanel());
        }
        Router.currentPage = switch (route){
            case "Demo" -> new DemoPage();
            case "Demo2" -> new Demo2Page();
            case "MainMenu" -> new MainMenuPage();
            case "Avenger" -> new AvengerAssembleGUI((Game) data); //game page
            case "Lobby2" -> new Lobby2(false,new ArrayList<LobbyProfile>());
            case "DeckCreator" -> new DeckCreatorPage();
            case "SelMode" -> new SelGameMode();
            case "Player" -> new RoomSelect();
            case "Lobby" -> new PlayerVsPlayer((NIOClient) data);
            case "Setting" -> new SettingPage();
            case "SelBot" -> new SelectBotRoom();
            default -> null;
        };

        layeredPane.add(currentPage.getMainPanel(),JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(currentPage.getOverlayPanel(),JLayeredPane.PALETTE_LAYER);
        mainFrame.setTitle(currentPage.getTitle());
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void refresh(){
        if (currentPage != null){
            currentPage.getMainPanel().setSize(mainFrame.getWidth(),mainFrame.getHeight());
            currentPage.getOverlayPanel().setSize(mainFrame.getWidth(),mainFrame.getHeight());
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(JFrame mainFrame) {
        Router.mainFrame = mainFrame;
    }

    public static JPanel getMainPanel(){
        return currentPage.getMainPanel();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Router.refresh();
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }
}
