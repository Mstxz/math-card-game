package GUI.Component;

import java.awt.*;

import javax.swing.*;

import AudioPlayer.SFXSwitcher;
import GUI.Page.AvengerAssembleGUI;

import java.awt.event.*;

import GUI.Page.Page;
import Gameplay.Card;
import Gameplay.Cards.AngryCat;
import utils.UIManager.CustomScrollBarUI;
import utils.ResourceLoader;
import utils.UIManager.RoundPanelUI;
import utils.SharedResource;
import utils.UIManager.CustomScrollBarUI;

public class CardInspector extends JPanel implements MouseListener, KeyListener {
	private Page				gui;
	private JLabel				img;
	private	JLabel				name;
	private	JLabel				description;
	private	JScrollPane			Pane;
	private JTextArea			desArea;
	private JPanel				ManaUsage;
	private	Card				card;

	public CardInspector(Card card, Page gui,ImageIcon img) {

		super();
		this.card = card;
		this.gui = gui;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.img = new JLabel(new ImageIcon(img.getImage().getScaledInstance((int)(SharedResource.CARD_WIDTH * 1.2), (int)(SharedResource.CARD_HEIGHT * 1.2),Image.SCALE_SMOOTH)));

		this.name = new JLabel(card.getName());
		this.name.setFont(SharedResource.getCustomSizeFont(72));
		this.name.setHorizontalAlignment(SwingConstants.CENTER);

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
		this.Pane.getViewport().setOpaque(false);
		this.Pane.setOpaque(false);
		this.Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.Pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		this.Pane.setPreferredSize(new Dimension(300, 150));
		this.Pane.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
		this.Pane.setMaximumSize(new Dimension(800, 150));

		this.ManaUsage = new JPanel(new GridLayout(1, 10));
		this.ManaUsage.setAlignmentX(LEFT_ALIGNMENT);
		this.ManaUsage.setMinimumSize(new Dimension(400, 70));
		this.ManaUsage.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));
		this.ManaUsage.setMaximumSize(new Dimension(400, 70));
		for (int i = 0;i<10;i++){
			this.ManaUsage.add(new ManaIcon(i <= card.getManaUsed(), i <= card.getManaUsed()));
		}

		JPanel p = new JPanel();
		p.setUI(new RoundPanelUI(SharedResource.SIAMESE_LIGHT));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(name);
		p.add(ManaUsage);
		p.add(Box.createRigidArea(new Dimension(0, 20)));
		p.add(Pane);
		p.setBorder(BorderFactory.createEmptyBorder(20,5,20,10));
		p.setPreferredSize(new Dimension(400, 300));

		JPanel m = new JPanel();
		m.setLayout(new BoxLayout(m, BoxLayout.LINE_AXIS));
		m.setUI(new RoundPanelUI(SharedResource.SIAMESE_BASE));
		m.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		m.setBackground(SharedResource.SIAMESE_BASE);
		m.setMinimumSize(new Dimension(600, 400));
		m.setPreferredSize(new Dimension(800, 400));
		m.setMaximumSize(new Dimension(800, 400));
		m.add(this.img);
		m.add(Box.createRigidArea(new Dimension(20, 0)));
		m.add(p);
		this.add(m);

		this.setSize(gui.getMainFrame().getWidth(), gui.getMainFrame().getHeight());
		this.addMouseListener(this);
		this.gui.getMainFrame().addKeyListener(this);
		this.setOpaque(false);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		SFXSwitcher.cardDescDown(card);
		this.removeAll();
		this.removeMouseListener(this);
		this.gui.getMainFrame().removeKeyListener(this);
		this.setVisible(false);
		gui.clearOverlay();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
			SFXSwitcher.cardDescDown(card);
			this.removeAll();
			this.removeMouseListener(this);
			this.gui.getMainFrame().removeKeyListener(this);
			this.setVisible(false);
			gui.clearOverlay();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

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
