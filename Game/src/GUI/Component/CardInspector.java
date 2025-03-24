package GUI.Component;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import AudioPlayer.SFXPlayer;
import GUI.Page.AvengerAssembleGUI;

import java.awt.event.*;

import Gameplay.Card;
import utils.ResourceLoader;
import utils.RoundPanelUI;
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

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.img = new JLabel(ResourceLoader.loadPicture(this.card.getPicture(), (int)(SharedResource.CARD_WIDTH * 1.2), (int)(SharedResource.CARD_HEIGHT * 1.2)));

		this.name = new JLabel(card.getName());
		this.name.setFont(SharedResource.getCustomSizeFont(72));
		this.name.setHorizontalAlignment(SwingConstants.CENTER);

		this.description = new JLabel(card.getDescription());
		this.description.setHorizontalAlignment(SwingConstants.CENTER);
		this.description.setFont(SharedResource.getCustomSizeFont(36));

		JPanel p = new JPanel();
		p.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(name);
		p.add(description);
		p.setBorder(BorderFactory.createEmptyBorder(50,5,50,50));
		p.setPreferredSize(new Dimension(400, 300));

		JPanel m = new JPanel();
		m.setLayout(new BoxLayout(m, BoxLayout.LINE_AXIS));
		m.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		m.setBackground(SharedResource.SIAMESE_BASE);
		m.setMinimumSize(new Dimension(600, 400));
		m.setPreferredSize(new Dimension(800, 600));
		m.add(img);
		m.add(Box.createRigidArea(new Dimension(20, 0)));
		m.add(p);
		this.add(m);

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
