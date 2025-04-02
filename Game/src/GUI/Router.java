package GUI;

import GUI.Component.Game;
import GUI.Component.LobbyProfile;
import GUI.Page.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

import GUI.Setting.Controller.SettingController;
import GUI.Setting.UserPreference;
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

        layeredPane = new JLayeredPane();
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/icon.png"));
            mainFrame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Error loading icon: " + e.getMessage());
        }
        mainFrame.setFocusable(true);
        mainFrame.setContentPane(layeredPane);
        SettingController.update();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (mainFrame.getDefaultCloseOperation() == WindowConstants.EXIT_ON_CLOSE){
                    UserPreference.writeFile();
                }
            }
        });
        mainFrame.setResizable(false);
        setRoute("MainMenu",null);
    }

    public static void setRoute(String route,Object data){
        if (currentPage != null){
            layeredPane.remove(currentPage.getMainPanel());
            layeredPane.remove(currentPage.getOverlayPanel());
        }
        Router.currentPage = switch (route){
            case "MainMenu" -> new MainMenuPage();
            case "Avenger" -> new AvengerAssembleGUI((Game) data); //game page
            case "DeckCreator" -> (Page) data;
            case "SelMode" -> new SelGameMode();
            case "Player" -> new RoomSelect();
            case "Lobby" -> new PlayerVsPlayer((NIOClient) data);
            case "Setting" -> new SettingPage();
            case "SelBot" -> new SelectBotRoom();
            case "Credit" -> new CreditPage();
            case "Statistics" -> new StatisticsPage();
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
