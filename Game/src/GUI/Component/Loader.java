package GUI.Component;

import GUI.Page.OverlayPlacement;
import GUI.Page.Page;
import GUI.Router;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;

public class Loader extends JPanel {
    private Page parentGUI;
    private String text;
    private JLabel loadingText;
    private JPanel loaderIcon;
    private JPanel tipsPanel;
    private JLabel tipsText;
    private JLabel klongPicture;
    private Thread runnerThread;
    private BufferedImage loaderImg;
    private Timer loaderSpinTimer;
    public Loader(Page parentGUI){
        this(parentGUI,"Loading...");
    }

    public Loader(Page parentGUI,String text){
        super();
        this.parentGUI = parentGUI;
        this.text = text;
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,30,30));
        tipsPanel = new JPanel();
        tipsPanel.setOpaque(false);

        tipsText = new JLabel("Tip: Did you know? Angy come from Angry Ha.");
        tipsText.setFont(SharedResource.getCustomSizeFont(20));

        loadingText = new JLabel(this.text);
        loadingText.setFont(SharedResource.getCustomSizeFont(36));

        loadingText.setHorizontalAlignment(SwingConstants.CENTER);
        loaderImg = ResourceLoader.loadBufferedPicture("assets/Loader.png");
        loaderIcon = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int x = (getWidth() - loaderImg.getWidth()) / 2;
                int y = (getHeight() - loaderImg.getHeight()) / 2;
                g2d.drawImage(loaderImg, x,y, this);
                g2d.dispose();
            }
        };
        loaderIcon.setPreferredSize(new Dimension(80,80));
        loaderIcon.setOpaque(false);
        setOpaque(false);

        tipsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
        tipsPanel.add(loaderIcon);
        tipsPanel.add(tipsText);

        klongPicture = new JLabel(ResourceLoader.loadPicture("assets/KlongFolder.webp",160,160));

        this.setSize(600,400);
        this.setLayout(new BorderLayout(0,20));
        this.add(loadingText,BorderLayout.NORTH);
        this.add(klongPicture,BorderLayout.CENTER);
        this.add(tipsPanel,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder(20,0,0,0));
        loaderSpinTimer = new Timer(40,new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                loaderImg = rotateLoader(loaderImg,45.0);
                loaderIcon.repaint();
            }
        });
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                while (!closeCondition()){
                    running();
                }
                loaderSpinTimer.stop();
                onClose();
            }
        };
        runnerThread = new Thread(runner);
        loaderSpinTimer.start();

    }

    private BufferedImage rotateLoader(BufferedImage bf,double angle){
        BufferedImage rotatedImg = new BufferedImage(loaderImg.getWidth(),loaderImg.getHeight(),loaderImg.getType());
        Graphics2D g2d = rotatedImg.createGraphics();
        g2d.rotate(Math.toRadians(angle),(double) bf.getWidth()/2, (double) bf.getHeight()/2);
        g2d.drawImage(bf,0,0,null);
        g2d.dispose();
        return rotatedImg;
    }



    public void startLoad(){
        parentGUI.setBackdropDim(true);
        parentGUI.showOverlay(this, OverlayPlacement.CENTER);
        revalidate();
        runnerThread.start();
    }
    public void running(){

    }
    public boolean closeCondition(){
        return true;
    }
    public void onClose(){

    }
}
