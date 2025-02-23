package GUI.Page;

import GUI.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoPage extends Page implements ActionListener {
    public DemoPage() {
        super("Demo");
        JButton button = new JButton("Yah uh");
        button.addActionListener(this);
        this.mainPanel.add(button);
        this.mainPanel.setVisible(true);
        this.mainFrame.setSize(1000,800);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Router.setRoute("Demo2",null);
    }
}
