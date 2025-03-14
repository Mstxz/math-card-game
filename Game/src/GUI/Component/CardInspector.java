package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.*;

import Gameplay.Card;

public class CardInspector extends JPanel implements KeyListener {
	private	Card	card;

	public CardInspector(Card card) {
		super();
		this.card = card;
		this.setBackground(new Color(0x00FF00AF, true));
		this.setSize(new Dimension(1920, 1080));
		this.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			this.removeAll();
			this.setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
