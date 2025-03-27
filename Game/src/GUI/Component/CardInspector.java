package GUI.Component;

import java.awt.*;

import javax.swing.*;

import AudioPlayer.SFXSwitcher;
import GUI.Page.AvengerAssembleGUI;

import java.awt.event.*;

import Gameplay.Card;
import utils.CustomScrollBarUI;
import utils.ResourceLoader;
import utils.RoundPanelUI;
import utils.SharedResource;

public class CardInspector extends JPanel implements MouseListener {
	private	AvengerAssembleGUI	gui;
	private JLabel				img;
	private	JLabel				name;
	private	JLabel				description;
	private	JScrollPane			Pane;
	private JTextArea			desArea;
	private JPanel				ManaUsage;
	private	Card				card;

	public CardInspector(Card card, AvengerAssembleGUI gui) {

		super();
		this.card = card;
		this.gui = gui;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.img = new JLabel(ResourceLoader.loadPicture(this.card.getPicture(), (int)(SharedResource.CARD_WIDTH * 1.2), (int)(SharedResource.CARD_HEIGHT * 1.2)));

		this.name = new JLabel(card.getName());
		this.name.setFont(SharedResource.getCustomSizeFont(72));
		this.name.setHorizontalAlignment(SwingConstants.CENTER);

		this.description = new JLabel(card.getDescription());
		this.description.setHorizontalAlignment(SwingConstants.CENTER);
		this.description.setFont(SharedResource.getCustomSizeFont(36));

		this.desArea = new JTextArea(card.getDescription());
		this.desArea.setForeground(SharedResource.SIAMESE_DARK);
		this.desArea.setOpaque(false);
		this.desArea.setEditable(false);
		this.desArea.addMouseListener(this);
		this.desArea.setFocusable(false);
		this.desArea.setFont(SharedResource.getCustomSizeFont(36));
		this.desArea.setWrapStyleWord(true);
		this.desArea.setLineWrap(true);

		this.Pane = new JScrollPane(desArea);
		this.Pane.setOpaque(false);
		this.Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.Pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		this.Pane.setPreferredSize(new Dimension(300, 150));
		this.Pane.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
//		this.Pane.setMinimumSize(new Dimension(200, 150));
		this.Pane.setMaximumSize(new Dimension(800, 150));

		this.ManaUsage = new JPanel(new GridLayout(1, 10));
		this.ManaUsage.setAlignmentX(LEFT_ALIGNMENT);
		this.ManaUsage.setMinimumSize(new Dimension(400, 70));
		this.ManaUsage.setMaximumSize(new Dimension(400, 70));
		this.ManaUsage.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));
		for (int i = 0;i<10;i++){
			if (i <= card.getManaUsed())
				this.ManaUsage.add(new ManaIcon(true, true));
			else
				this.ManaUsage.add(new ManaIcon(false, false));
		}

		JPanel p = new JPanel();
		p.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(name);
		p.add(Pane);
//		p.add(description);
		p.add(Box.createRigidArea(new Dimension(0, 20)));
		p.add(ManaUsage);
		p.setBorder(BorderFactory.createEmptyBorder(20,5,20,10));
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
		SFXSwitcher.cardDescDown(card);
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
