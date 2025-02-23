package GUI.Page;

import GUI.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo2Page extends Page implements ActionListener {
    public Demo2Page() {
        super("Demo2");
        JButton button = new JButton("Nuh uh");
        button.addActionListener(this);
        this.mainPanel.add(button);
        this.mainPanel.setVisible(true);
        this.mainFrame.setSize(1000,500);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Router.setRoute("Demo",null);
    }
}
