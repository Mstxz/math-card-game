package GUI.UIManager;

import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.geom.Path2D;

public class ComboBoxUI extends BasicComboBoxUI{
    @Override
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setColor(SharedResource.SIAMESE_BRIGHT);
        g.fillRoundRect(bounds.x,bounds.y,bounds.width,bounds.height,20,20);
        g.fillRect(bounds.width-20,bounds.y,20,20);
        g.fillRect(bounds.width-20,bounds.height-20,20,20);
        g.setColor(SharedResource.SIAMESE_DARK);
        g.setFont(SharedResource.getCustomSizeFont(20));
        g.drawString(comboBox.getSelectedItem().toString(),bounds.x+20,bounds.y+bounds.height/2+5);
    }

    @Override
    protected JButton createArrowButton() {
        BasicArrowButton bt = new BasicArrowButton(
                BasicArrowButton.SOUTH,
                SharedResource.SIAMESE_BRIGHT, new Color(0,0,0,0),
                SharedResource.SIAMESE_DARK, new Color(0,0,0,0)){
            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getParent().getBackground());
                g2d.fillRect(0,0,getWidth(),getHeight());

                g2d.setColor(SharedResource.SIAMESE_BRIGHT);
                g.fillRoundRect(0,0,getWidth(),getHeight(),20,20);

                g2d.fillRect(0,0,20,20);
                g2d.fillRect(0,getHeight()-20,20,20);

                Image icon = ResourceLoader.loadPicture("assets/Component/DownArrow.png",22,19).getImage();
                g2d.drawImage(icon,5,10,null);
            }

            @Override
            public boolean isFocusable() {
                return false;
            }

        };

        return bt;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        // First, paint the default components (background, arrow, etc.)
        super.paint(g, c);

        // Custom painting for the border
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the border color and stroke
        g2.setColor(Color.BLUE); // Border color (set to blue)
        g2.setStroke(new BasicStroke(3)); // Border thickness

        // Get the rectangle representing the JComboBox dimensions
        Rectangle r = c.getBounds();

        // Draw a rounded border around the JComboBox
        g2.drawRoundRect(0, 0, r.width - 1, r.height - 1, 20, 20); // 15 is the corner radius

        g2.dispose();
    }


    public static void main(String[] args) {
        SharedResource.loadFont();
        // Create a JFrame
        JFrame frame = new JFrame("JComboBox Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200); // Set the size of the frame

        // Create a JComboBox with some items
        String[] items = {"Option 1", "Option 2", "Option 3", "Option 4"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setSize(new Dimension(100,50));

        comboBox.setUI(new ComboBoxUI());

        // Add the combo box to the frame
        frame.setLayout(null);
        comboBox.setBounds(50, 50, 200, 40);
        frame.add(comboBox);

        // Make the frame visible
        frame.setVisible(true);
    }
}
