package GUI.Component;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import Gameplay.Card;
import utils.ResourceLoader;
import utils.SharedResource;

public class CardPlayable extends JButton implements MouseListener {
	private HandDeck handDeck;
	private	Card 	card;
	private	boolean	isEnemy;
	private	int		OLD_WIDTH;
	private	int		OLD_HEIGHT;
	private boolean isPlayable;
	public CardPlayable(HandDeck hand,Card card, double scale, boolean isEnemy,boolean isPlayable) {
		super();
		this.handDeck = hand;
		this.card = card;
		this.isEnemy = isEnemy;
		this.setPlayable(isPlayable);
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		this.setIcon();
		this.addMouseListener(this);
		//System.out.println(card.getName());
	}

	public void	rescale(double scale)
	{
		this.setSize((int)(SharedResource.CARD_WIDTH * scale), (int)(SharedResource.CARD_HEIGHT * scale));
		this.setIcon();
	}

	public void	setIcon()
	{
		if (!isEnemy) {
			System.out.println(this.card.getPicture());
			this.setIcon(ResourceLoader.loadPicture(this.card.getPicture(), this.getWidth(), this.getHeight()));
			if (!isPlayable) {
				this.setEnabled(false);
				//this.setBorder(new LineBorder(Color.RED, 3));
			}
		}
		else {
			this.setIcon(ResourceLoader.loadPicture("assets/BackSideCard.png", this.getWidth(), this.getHeight()));


		}
	}

	public void setPlayable(boolean playable) {
		this.isPlayable = playable;
	}

	@Override
	public void	setSize(int width, int height) {
		super.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		 if (isEnemy)
		 	return ;
		this.OLD_WIDTH = this.getWidth();
		this.OLD_HEIGHT = this.getHeight();
		this.setForeground(new Color(0x1E1E1E5F));
		this.setSize((int)(this.getWidth() * 1.2), (int)(this.getHeight() * 1.2));
		this.setIcon();
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3 && !handDeck.gui.isBlocked)
		{
			JPanel	overlay;

			// handDeck.gui.showOverlay(
			// 	new JLabel(ResourceLoader.loadPicture(
			// 					this.card.getPicture(),
			// 					this.getWidth(),
			// 					this.getHeight())
			// 				),
			// 				0,
			// 				0,
			// 				handDeck.gui.getMainFrame().getWidth(),
			// 				handDeck.gui.getMainFrame().getHeight()
			// );
			// handDeck.gui.showOverlay(
			// 				new CardInspector(card, handDeck.gui),
			// 				0,
			// 				0,
			// 				handDeck.gui.getMainFrame().getWidth(),
			// 				handDeck.gui.getMainFrame().getHeight()
			// );
			overlay = handDeck.gui.getOverlayPanel();
			// overlay.setLayout(new BorderLayout());
			// overlay.add(new JLabel(ResourceLoader.loadPicture(this.card.getPicture(),this.getWidth(),this.getHeight())), BorderLayout.PAGE_START);
			// overlay.add(new JLabel(ResourceLoader.loadPicture("assets/Green_HP-1.png",this.getWidth(),this.getHeight())), BorderLayout.PAGE_START);
			overlay.add(new CardInspector(card, handDeck.gui));
			// overlay.addKeyListener(new KeyAdapter() {
			// 	public void	keyPressed(KeyEvent e)
			// 	{
			// 		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// 			System.out.println("CLOSE");
			// 			handDeck.gui.isBlocked = false;
			// 			handDeck.gui.clearOverlay();
			// 			overlay.removeKeyListener(this);
			// 		}
			// 	}
			// });
			// overlay.addMouseListener(new MouseListener() {
			// 	@Override
			// 	public void mouseClicked(MouseEvent e) {
			// 		handDeck.gui.clearOverlay();
			// 		handDeck.gui.isBlocked = false;
			// 		overlay.removeMouseListener(this);
			// 	}

			// 	@Override
			// 	public void mouseEntered(MouseEvent e) {}

			// 	@Override
			// 	public void mouseExited(MouseEvent e) {}

			// 	@Override
			// 	public void mousePressed(MouseEvent e) {}

			// 	@Override
			// 	public void mouseReleased(MouseEvent e) {}
			// });
			handDeck.gui.setBackdropDim(true);
			// handDeck.gui.setOverlayPanel(handDeck);
			// System.out.println(card.getDescription());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (isEnemy || handDeck.gui.isBlocked)
			return ;
		this.setSize(OLD_WIDTH, OLD_HEIGHT);
		this.setIcon();
	}

	public Card getCard() {
		return card;
	}

	public boolean isPlayable() {
		return isPlayable;
	}
}
