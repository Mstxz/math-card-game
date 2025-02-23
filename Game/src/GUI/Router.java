package GUI;

import GUI.Page.Demo2Page;
import GUI.Page.DemoPage;
import GUI.Page.MainMenuPage;
import GUI.Page.Page;

import javax.swing.*;

public class Router {
    private static Page currentPage;
    private static JFrame mainFrame;

    public Router() {
        mainFrame = new JFrame();
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
