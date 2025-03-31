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

    public MenuConfirm(Page page, String label) {
        super(page);
        setupButton();

        JLabel title = new JLabel(label);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(SharedResource.getFont48());

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(btnPanel);
        this.add(Box.createVerticalBox());
//        System.out.println(this.getKeyListeners());
    }

    private void setupButton() {
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
