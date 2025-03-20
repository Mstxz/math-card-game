package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

import AudioPlayer.SFXPlayer;
import GUI.Page.AvengerAssembleGUI;

import java.awt.event.*;

import Gameplay.Card;
import utils.ResourceLoader;
import utils.SharedResource;

public class CardInspector extends JPanel implements MouseListener {
	private	AvengerAssembleGUI	gui;
	private JLabel				img;
	private	JLabel				name;
	private	JLabel				description;
	private	Card				card;

	public CardInspector(Card card, AvengerAssembleGUI gui) {
		super();
		this.card = card;
		this.gui = gui;
		
		this.setLayout(new BorderLayout());
		this.img = new JLabel(ResourceLoader.loadPicture(this.card.getPicture(), SharedResource.CARD_WIDTH * 2, SharedResource.CARD_HEIGHT * 2));

		this.name = new JLabel(card.getName());
		this.name.setFont(SharedResource.getCustomSizeFont(72));
		this.name.setHorizontalAlignment(SwingConstants.CENTER);
		this.description = new JLabel(card.getDescription());
		this.description.setHorizontalAlignment(SwingConstants.CENTER);
		this.description.setFont(SharedResource.getCustomSizeFont(36));

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setOpaque(false);
		p.setAlignmentX(CENTER_ALIGNMENT);
		p.add(name);
		p.add(description);
		this.add(img, BorderLayout.NORTH);
		this.add(p, BorderLayout.CENTER);
		

		this.setSize(gui.getMainFrame().getWidth(), gui.getMainFrame().getHeight());
		this.addMouseListener(this);
		this.setOpaque(false);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Desc_Down.wav", -10.0f);
		this.removeAll();
		this.removeMouseListener(this);
		this.setVisible(false);
		gui.clearOverlay();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
