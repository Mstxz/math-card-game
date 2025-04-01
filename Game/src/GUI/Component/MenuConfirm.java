package GUI.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import GUI.Page.Page;
import utils.SharedResource;
import utils.UIManager.ButtonUI;

import javax.swing.*;

public abstract class MenuConfirm extends MenuOverlay implements ActionListener {
    private final JPanel  btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
    private JButton confirmButton;
    private JButton deniedButton;

    public MenuConfirm(Page page, String label, JComponent midComp){
        super(page, new BorderLayout(0, 20));
        setupButton();

        JLabel title = new JLabel(label);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(SharedResource.getCustomSizeFont(36));

        this.add(title, BorderLayout.NORTH);
        this.add(midComp, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
    }

    public MenuConfirm(Page page, String label) {
        super(page);
        setupButton();

        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel(label);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(SharedResource.getCustomSizeFont(36));
        p.setMaximumSize(new Dimension(1200, 40));
        p.setOpaque(false);
        p.add(title);

        this.add(Box.createVerticalGlue());
        this.add(p);
        this.add(btnPanel);
        this.add(Box.createVerticalGlue());
    }

    private void setupButton() {
        btnPanel.setMaximumSize(new Dimension(1200, 60));

        confirmButton = new JButton("Yes");
        confirmButton.setUI(new ButtonUI());
        confirmButton.addActionListener(this);
        confirmButton.setPreferredSize(new Dimension(150,60));

        deniedButton = new JButton("No");
        deniedButton.setUI(new ButtonUI());
        deniedButton.addActionListener(this);
        deniedButton.setPreferredSize(new Dimension(150,60));

        btnPanel.add(confirmButton);
        btnPanel.add(deniedButton);
        btnPanel.setOpaque(false);
    }

    public abstract void onConfirm();

    public abstract void onDenied();

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            onConfirm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(confirmButton)){
            onConfirm();
        } else if (e.getSource().equals(deniedButton)) {
            onDenied();
        }
    }
}
