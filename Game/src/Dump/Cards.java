package Dump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cards extends JPanel {
    private int x, y, width, height;
    private Color color;
    private int originalY, originalHeight;
    private static final int TARGET_WIDTH = 330;
    private static final int TARGET_HEIGHT = 450;
    private static final int ARC = 20;

    public Cards(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.originalY = y;
        this.originalHeight = height;

        setBounds(x, y, width, height); // Initial size and position

        // Mouse event handlers
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                increaseSizeUpwards(); // Increase size upwards
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetSize(); // Restore original size and position
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRoundRect(0, 0, width, height, ARC, ARC);
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        repaint();
    }

    private void increaseSizeUpwards() {
        int heightIncrease = TARGET_HEIGHT - originalHeight; // How much height increases
        this.y -= heightIncrease; // Move up to keep the bottom fixed
        this.width = TARGET_WIDTH;
        this.height = TARGET_HEIGHT;
        setBounds(x, y, width, height); // Update panel position & size
        repaint();
    }

    private void resetSize() {
        this.y = originalY;
        this.width = 165;
        this.height = originalHeight;
        setBounds(x, y, width, height);
        repaint();
    }
}
